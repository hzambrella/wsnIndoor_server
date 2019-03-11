package com.hz.wsnIndoorBack.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.alibaba.fastjson.JSONObject;
import com.hz.wsnIndoorBack.DTO.Result;
import com.hz.wsnIndoorBack.libSVMUtils.LibSVMApi;
import com.hz.wsnIndoorBack.libSVMUtils.dto.AnchorData;
import com.hz.wsnIndoorBack.libSVMUtils.dto.HigherAnchorDTO;
import com.hz.wsnIndoorBack.libSVMUtils.dto.NormalAnchorDTO;
import com.hz.wsnIndoorBack.libSVMUtils.model.LearningParam;
import com.hz.wsnIndoorBack.mapper.NetMapper;
import com.hz.wsnIndoorBack.model.Anchor;
import com.hz.wsnIndoorBack.service.IAnchorLocService;

@Service
@EnableConfigurationProperties(LearningParam.class)
public class AnchorLocServiceImpl implements IAnchorLocService {
	@Autowired
	private NetMapper netMapper;

	@Value("${learning.rootDir}")
	private String learningDir;

	@Value("${learning.higherAnchorHopUrl}")
	private String higherAnchorHopUrl;

	@Value("${learning.normalAnchorHopUrl}")
	private String normalAnchorHopUrl;

	@Value("${learning.trainXPath}")
	private String trainXPath;

	@Value("${learning.trainYPath}")
	private String trainYPath;

	@Value("${learning.testXPath}")
	private String testXPath;

	@Value("${learning.testYPath}")
	private String testYPath;

	@Value("${learning.modelXPath}")
	private String modelXPath;

	@Value("${learning.modelYPath}")
	private String modelYPath;

	@Value("${learning.outputXPath}")
	private String outputXPath;

	@Value("${learning.outputYPath}")
	private String outputYPath;

	@Value("${learning.resultXPath}")
	private String resultXPath;

	@Value("${learning.resultYPath}")
	private String resultYPath;

	@Value("${learning.trainParams}")
	private String trainParams;

	@Value("${learning.predicParams}")
	private String predicParams;

	@Value("${learning.upper}")
	private int upper;

	@Value("${learning.lower}")
	private int lower;

	@Value("${learning.labelMinNum}")
	private int labelMinNum;

	@Value("${learning.labelMaxNum}")
	private int labelMaxNum;

	@Value("${learning.attrMinNum}")
	private int attrMinNum;

	@Value("${learning.attrMaxNum}")
	private int attrMaxNum;

	@Value("${learning.testSinkHost}")
	private String testSinkHost;

	@Override
	public Result<Object> floodAndTrain(Integer nid) {
		Result<Object> result = new Result<>();
		try {
			// 训练文件的根目录
			File learningDir = ResourceUtils.getFile(this.learningDir);
			String rootDir = learningDir.getAbsolutePath();

			// TODO：时间戳
			// String timeStamp = String.valueOf(System.currentTimeMillis());
			String timeStamp = "112";

			String trainXFileName = rootDir + File.separator
					+ String.format(trainXPath, nid, timeStamp);
			String trainYFileName = rootDir + File.separator
					+ String.format(trainYPath, nid, timeStamp);

			String modelXFilePath = rootDir + File.separator
					+ String.format(modelXPath, nid, timeStamp);
			String modelYFilePath = rootDir + File.separator
					+ String.format(modelYPath, nid, timeStamp);

			// TODO:md5校验
			File modelXFile = new File(modelXFilePath);
			File modelYFile = new File(modelYFilePath);

			if (!(modelXFile.exists() && modelYFile.exists()
					&& modelXFile.length() != 0 && modelYFile.length() != 0)) {
				// 模型文件不存在时，向网关发送洪泛请求。返回锚节点之间的跳距hop
				// TODO:从数据库中读取网关的host而非配置文件
				String sinkHost = testSinkHost;
				CloseableHttpClient httpclient = HttpClients.createDefault();
				String getHigherAnchorHopUrl = sinkHost
						+ String.format(higherAnchorHopUrl, nid);
				HttpGet httpget = new HttpGet(getHigherAnchorHopUrl);
				CloseableHttpResponse response;
				response = httpclient.execute(httpget);

				// 判断返回状态是否为200
				if (response.getStatusLine().getStatusCode() == 200) {
					// 获取服务端返回的数据
					String content = EntityUtils.toString(response.getEntity(),
							"UTF-8");
					HigherAnchorDTO han = JSONObject.parseObject(content,
							HigherAnchorDTO.class);

					File fileTrainX = new File(trainXFileName);
					if (!fileTrainX.exists()) {
						fileTrainX.createNewFile();
					}

					File fileTrainY = new File(trainYFileName);
					if (!fileTrainY.exists()) {
						fileTrainY.createNewFile();
					}

					// 将训练数据按照格式写入文件
					FileWriter finTrainX = new FileWriter(fileTrainX, false);
					FileWriter finTrainY = new FileWriter(fileTrainY, false);
					// 清空内容
					finTrainX.write("");
					finTrainY.write("");
					finTrainX.flush();
					finTrainY.flush();

					int row = 0;
					for (AnchorData anchor : han.getAnchors()) {
						StringBuffer trainXBuf = new StringBuffer();
						StringBuffer trainYBuf = new StringBuffer();

						// 归一化
						double x = LibSVMApi.svmScale(true,
								Double.valueOf(anchor.getX()), lower, upper,
								labelMinNum, labelMaxNum);
						double y = LibSVMApi.svmScale(true,
								Double.valueOf(anchor.getY()), lower, upper,
								labelMinNum, labelMaxNum);
						trainXBuf.append(x);
						trainYBuf.append(y);
						trainXBuf.append(" ");
						trainYBuf.append(" ");

						int[] hop = han.getHop()[row];

						for (int col = 0; col < hop.length; col++) {
							double h = LibSVMApi.svmScale(true,
									Double.valueOf(hop[col]), lower, upper,
									attrMinNum, attrMaxNum);

							trainXBuf.append(col + 1);
							trainXBuf.append(":");
							trainXBuf.append(h);

							trainYBuf.append(col + 1);
							trainYBuf.append(":");
							trainYBuf.append(h);
							if (col < hop.length - 1) {
								trainXBuf.append(" ");
								trainYBuf.append(" ");
							} else {
								trainXBuf.append("\n");
								trainYBuf.append("\n");
							}
						}
						finTrainX.write(trainXBuf.toString());
						finTrainY.write(trainYBuf.toString());
						row++;
					}
					finTrainX.close();
					finTrainY.close();
				} else {
					// 如果网关返回的不是200。
					System.out.println(response.getStatusLine());
					result.setCode(response.getStatusLine().getStatusCode());
					result.setMessage("远程网关错误");
				}

				// 清空模型文件
				new File(modelXFilePath).delete();
				new File(modelYFilePath).delete();
				// 训练，模型会保存在model文件中
				LibSVMApi.svmTrain(trainParams, trainXFileName, modelXFilePath);
				LibSVMApi.svmTrain(trainParams, trainYFileName, modelYFilePath);

				// 关闭httpClient连接
				if (response != null) {
					response.close();
				}
				httpclient.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			result.setCode(Result.SystemError);
			result.setMessage("系统异常");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result.setCode(Result.SystemError);
			result.setMessage("服务异常");
		} catch (IOException e) {
			e.printStackTrace();
			result.setCode(Result.SystemError);
			result.setMessage("服务异常");
		}

		result.setMessage("完成洪泛阶段");
		return result;
	}

	@Override
	public Result<Object> predict(Integer nid) {
		Result<Object> result = new Result<>();
		try {
			// 训练文件的根目录
			File learningDir = ResourceUtils.getFile(this.learningDir);
			String rootDir = learningDir.getAbsolutePath();
			
			//TODO:查询数据表build_network中的anchor_status字段。
			// 如果是完成阶段，直接从数据库中读取数据，不需要重复预测。
			// TODO：时间戳
			// String timeStamp = String.valueOf(System.currentTimeMillis());
			String timeStamp = "112";

			String modelXFilePath = rootDir + File.separator
					+ String.format(modelXPath, nid, timeStamp);
			String modelYFilePath = rootDir + File.separator
					+ String.format(modelYPath, nid, timeStamp);

			String testXFilePath = rootDir + File.separator
					+ String.format(testXPath, nid, timeStamp);
			String testYFilePath = rootDir + File.separator
					+ String.format(testYPath, nid, timeStamp);

			String outputXFilePath = rootDir + File.separator
					+ String.format(outputXPath, nid, timeStamp);
			String outputYFilePath = rootDir + File.separator
					+ String.format(outputYPath, nid, timeStamp);

			String resultXFilePath = rootDir + File.separator
					+ String.format(resultXPath, nid, timeStamp);
			String resultYFilePath = rootDir + File.separator
					+ String.format(resultYPath, nid, timeStamp);

			// 是否已经训练
			// TODO:md5校验训练文件
			File modelXFile = new File(modelXFilePath);
			File modelYFile = new File(modelYFilePath);

			if (!(modelXFile.exists() && modelYFile.exists()
					&& modelXFile.length() != 0 && modelYFile.length() != 0)) {
				result.setCode(Result.SystemError);
				result.setMessage("模型文件丢失，请重新进行洪泛阶段");
				return result;
			}

			// 向网关发送请求。返回普通锚节点到关键锚节点之间的跳距hop
			// TODO:从数据库中读取网关的host而非配置文件
			String sinkHost = testSinkHost;
			CloseableHttpClient httpclient = HttpClients.createDefault();
			String getNormalAnchorHopUrl = sinkHost
					+ String.format(normalAnchorHopUrl, nid);
			HttpGet httpget = new HttpGet(getNormalAnchorHopUrl);
			CloseableHttpResponse response;
			response = httpclient.execute(httpget);

			if (response.getStatusLine().getStatusCode() == 200) {
				// 获取服务端返回的数据
				String content = EntityUtils.toString(response.getEntity(),
						"UTF-8");
				NormalAnchorDTO han = JSONObject.parseObject(content,
						NormalAnchorDTO.class);

				File fileTestX = new File(testXFilePath);
				if (!fileTestX.exists()) {
					fileTestX.createNewFile();
				}

				File fileTestY = new File(testYFilePath);
				if (!fileTestY.exists()) {
					fileTestY.createNewFile();
				}

				FileWriter finTestX = new FileWriter(fileTestX, false);
				FileWriter finTestY = new FileWriter(fileTestY, false);
				finTestX.write("");
				finTestX.flush();
				finTestY.write("");
				finTestY.flush();

				int[][] hops = han.getHop();

				// 按照格式填入预测/测试特征数据
				for (int i = 0; i < hops.length; i++) {
					StringBuffer testXBuf = new StringBuffer();
					StringBuffer testYBuf = new StringBuffer();
					// 预测的时候，标签的值填0
					testXBuf.append(0);
					testYBuf.append(0);
					testXBuf.append(" ");
					testYBuf.append(" ");

					for (int col = 0; col < hops[i].length; col++) {
						double h = LibSVMApi.svmScale(true,
								Double.valueOf(hops[i][col]), lower, upper,
								attrMinNum, attrMaxNum);
						testXBuf.append(col + 1);
						testXBuf.append(":");
						testXBuf.append(h);

						testYBuf.append(col + 1);
						testYBuf.append(":");
						testYBuf.append(h);
						if (col < hops[i].length - 1) {
							testXBuf.append(" ");
							testYBuf.append(" ");
						} else {
							testXBuf.append("\n");
							testYBuf.append("\n");
						}
					}
					finTestX.write(testXBuf.toString());
					finTestY.write(testYBuf.toString());
				}
				finTestX.close();
				finTestY.close();
				LibSVMApi.svmPredict(predicParams, testXFilePath,
						modelXFilePath, outputXFilePath);
				LibSVMApi.svmPredict(predicParams, testYFilePath,
						modelYFilePath, outputYFilePath);

				// 预测结果反归一化，得到最终结果
				File outputXFile = new File(outputXFilePath);
				File outputYFile = new File(outputYFilePath);
				ArrayList<Double> xresult = new ArrayList<Double>();
				ArrayList<Double> yresult = new ArrayList<Double>();
				Scanner scannerX = new Scanner(new FileInputStream(outputXFile));
				Scanner scannerY = new Scanner(new FileInputStream(outputYFile));
				while (scannerX.hasNextDouble()) {
					xresult.add(LibSVMApi.svmScale(false,
							scannerX.nextDouble(), lower, upper, labelMinNum,
							labelMaxNum));
				}

				while (scannerY.hasNextDouble()) {
					yresult.add(LibSVMApi.svmScale(false,
							scannerY.nextDouble(), lower, upper, labelMinNum,
							labelMaxNum));
				}

				scannerX.close();
				scannerY.close();

				File resultXFile = new File(resultXFilePath);
				File resultYFile = new File(resultYFilePath);
				StringBuffer resultXBuf = new StringBuffer();
				StringBuffer resultYBuf = new StringBuffer();

				for (double x : xresult) {
					resultXBuf.append(x);
					resultXBuf.append("\n");
				}

				for (double y : yresult) {
					resultYBuf.append(y);
					resultYBuf.append("\n");
				}

				FileWriter finResultX = new FileWriter(resultXFile, false);
				FileWriter finResultY = new FileWriter(resultYFile, false);
				finResultX.write(resultXBuf.toString());
				finResultY.write(resultYBuf.toString());
				finResultX.close();
				finResultY.close();

				// 解析最终的预测结果
				ArrayList<Anchor> anchors = new ArrayList<>();

				Scanner scannerResultX = new Scanner(new FileInputStream(
						resultXFile));
				Scanner scannerResultY = new Scanner(new FileInputStream(
						resultYFile));
				int[] anchorIds = han.getAnchorIds();
				for (int i = 0; i < anchorIds.length; i++) {
					Anchor anchor = new Anchor();
					// TODO:目前是golang模拟网关发过来的id，nid乘以1000+id作为锚节点id。
					anchor.setAnchorId(nid*1000+anchorIds[i]);
					if (scannerResultX.hasNextFloat()) {
						anchor.setX(scannerResultX.nextFloat());
					}

					if (scannerResultY.hasNextFloat()) {
						anchor.setY(scannerResultY.nextFloat());
					}
					anchor.setNid(nid);
					anchor.setAnchorType(Anchor.NormalAnchor);
					anchor.setStatus(Anchor.Open);
					anchors.add(anchor);
				}

				// 持久化到数据库
				for (Anchor anchor:anchors){
					netMapper.saveAnchorLocResult(anchor);
				}
				
				scannerResultX.close();
				scannerResultY.close();
				result.setObj(anchors);
			} else {
				// 如果网关返回的不是200。
				System.out.println(response.getStatusLine());
				result.setCode(response.getStatusLine().getStatusCode());
				result.setMessage("远程网关错误");
			}

			// 关闭httpClient连接
			if (response != null) {
				response.close();
			}
			httpclient.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			result.setCode(Result.SystemError);
			result.setMessage("系统异常");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result.setCode(Result.SystemError);
			result.setMessage("服务异常");
		} catch (IOException e) {
			e.printStackTrace();
			result.setCode(Result.SystemError);
			result.setMessage("服务异常");
		}
		return result;
	}
}
