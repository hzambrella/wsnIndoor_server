package com.hz.wsnIndoorBack.libSVMUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.hz.wsnIndoorBack.libSVMUtils.dto.AnchorData;
import com.hz.wsnIndoorBack.libSVMUtils.dto.HigherAnchorDTO;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class test {
	private static String getHigherAnchorHopUrlTemp = "http://localhost:8111/wsnIndoor/getHigherAnchorHop?nid=%d";
	private static String getNormalAnchorHopUrlTemp = "http://localhost:8111/wsnIndoor/getNormalAnchorHop?nid=%d";
	private static String learningTempPath="src/main/resources/learning_temp";
	private static String trainXPathNameTemp = learningTempPath+"/indoor_train_x_%d_%s.data";
	private static String trainYPathNameTemp = learningTempPath+"/indoor_train_y_%d_%s.data";
	private static String testXPathNameTemp = learningTempPath+"/indoor_test_x_%d_%s.data";
	private static String testYPathNameTemp = learningTempPath+"/indoor_test_y_%d_%s.data";
	private static String modelXPathTemp = learningTempPath+"/indoor_model_x_%d_%s.model";
	private static String modelYPathTemp = learningTempPath+"/indoor_model_y_%d_%s.model";
	private static String outputXPathTemp = learningTempPath+"/indoor_output_x_%d_%s.model";
	private static String outputYPathTemp = learningTempPath+"/indoor_output_y_%d_%s.model";
	private static String resultXPathTemp = learningTempPath+"/indoor_result_x_%d_%s.model";
	private static String resultYPathTemp = learningTempPath+"/indoor_result_y_%d_%s.model";

	private static String trainParams = "-s 3 -t 2 -c 100 -g 0.01 -p 0";
	private static String predicParams = "";

	private static int upper = 1;
	private static int lower = 0;
	private static int labelMinNum = 10;
	private static int labelMaxNum = 60;
	private static int attrMinNum = 0;
	private static int attrMaxNum = 13;

	public static void main(String[] args) throws IOException {
		/*
		 * File file=new File("resources/test.txt"); if (!file.exists()){
		 * file.createNewFile(); }
		 * 
		 * System.out.println(file.getAbsolutePath());
		 */
		testWebInterface();
	}

	private static void testWebInterface() throws ClientProtocolException,
			IOException {
		File learningTempDir=new File(learningTempPath);
		if (!learningTempDir.exists()){
			learningTempDir.mkdir();
		}
		System.out.println(learningTempDir.getAbsolutePath());
		
		int nid = 1;
		// 时间戳
		// String timeStamp = String.valueOf(System.currentTimeMillis());
		String timeStamp = "111";

		String trainXFileName = String.format(trainXPathNameTemp, nid,
				timeStamp);
		String trainYFileName = String.format(trainYPathNameTemp, nid,
				timeStamp);
		String testXFileName = String.format(testXPathNameTemp, nid, timeStamp);
		String testYFileName = String.format(testYPathNameTemp, nid, timeStamp);
		
		String modelXPath = String.format(modelXPathTemp, nid, timeStamp);
		String modelYPath = String.format(modelYPathTemp, nid, timeStamp);

		String outputXPath = String.format(outputXPathTemp, nid, timeStamp);
		String outputYPath = String.format(outputYPathTemp, nid, timeStamp);
		
		String resultXPath = String.format(resultXPathTemp, nid, timeStamp);
		String resultYPath = String.format(resultYPathTemp, nid, timeStamp);

		CloseableHttpClient httpclient = HttpClients.createDefault();
		String getHigherAnchorHopUrl = String.format(getHigherAnchorHopUrlTemp,
				nid);
		HttpGet httpget = new HttpGet(getHigherAnchorHopUrl);
		CloseableHttpResponse response = httpclient.execute(httpget);
		try {
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
				FileWriter finTrainX = new FileWriter(fileTrainX, false);
				FileWriter finTrainY = new FileWriter(fileTrainY, false);

				int row = 0;
				for (AnchorData anchor : han.getAnchors()) {
					StringBuffer trainXBuf = new StringBuffer();
					StringBuffer trainYBuf = new StringBuffer();
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
						double h=LibSVMApi.svmScale(true,
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

				LibSVMApi.svmTrain(trainParams, trainXFileName, modelXPath);
				LibSVMApi.svmTrain(trainParams, trainYFileName, modelYPath);
			}

			CloseableHttpClient httpclient2 = HttpClients.createDefault();
			String getNormalAnchorHopUrl = String.format(
					getNormalAnchorHopUrlTemp, nid);
			HttpGet httpget2 = new HttpGet(getNormalAnchorHopUrl);
			CloseableHttpResponse response2 = httpclient.execute(httpget2);
			try {
				// 判断返回状态是否为200
				if (response.getStatusLine().getStatusCode() == 200) {
					// 获取服务端返回的数据
					String content = EntityUtils.toString(
							response2.getEntity(), "UTF-8");
					HigherAnchorDTO han = JSONObject.parseObject(content,
							HigherAnchorDTO.class);

					File fileTestX = new File(testXFileName);
					if (!fileTestX.exists()) {
						fileTestX.createNewFile();
					}

					File fileTestY = new File(testYFileName);
					if (!fileTestY.exists()) {
						fileTestY.createNewFile();
					}
					FileWriter finTestX = new FileWriter(fileTestX, false);
					FileWriter finTestY = new FileWriter(fileTestY, false);
					int[][] hops = han.getHop();
					for (int i = 0; i < hops.length; i++) {
						StringBuffer testXBuf = new StringBuffer();
						StringBuffer testYBuf = new StringBuffer();
						testXBuf.append(0);
						testYBuf.append(0);
						testXBuf.append(" ");
						testYBuf.append(" ");

						for (int col = 0; col < hops[i].length; col++) {
							double h=LibSVMApi.svmScale(true,
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
					LibSVMApi.svmPredict(predicParams, testXFileName,
							modelXPath, outputXPath);
					LibSVMApi.svmPredict(predicParams, testYFileName,
							modelYPath, outputYPath);
					
					File outputXFile=new File(outputXPath);
					File outputYFile=new File(outputYPath);
					ArrayList<Double>xresult=new ArrayList<Double>();
					ArrayList<Double>yresult=new ArrayList<Double>();
					Scanner scannerX=new Scanner(new FileInputStream(outputXFile));
					Scanner scannerY=new Scanner(new FileInputStream(outputYFile));
					while(scannerX.hasNextDouble()){
						xresult.add(LibSVMApi.svmScale(false,
								scannerX.nextDouble(), lower, upper,
								labelMinNum, labelMaxNum));
					}
					
					while(scannerY.hasNextDouble()){
						yresult.add(LibSVMApi.svmScale(false,
								scannerY.nextDouble(), lower, upper,
								labelMinNum, labelMaxNum));
					}
					
					scannerX.close();
					scannerY.close();
					
					File resultXFile=new File(resultXPath);
					File resultYFile=new File(resultYPath);
					StringBuffer resultXBuf = new StringBuffer();
					StringBuffer resultYBuf = new StringBuffer();
					
					for(double x:xresult){
						resultXBuf.append(x);
						resultXBuf.append("\n");
					}
					
					for (double y:yresult){
						resultYBuf.append(y);
						resultYBuf.append("\n");
					}
					
					FileWriter finResultX = new FileWriter(resultXFile, false);
					FileWriter finResultY = new FileWriter(resultYFile, false);
					finResultX.write(resultXBuf.toString());
					finResultY.write(resultYBuf.toString());
					finResultX.close();
					finResultY.close();
				}
			} finally {
				if (response2 != null) {
					response.close();
				}
				httpclient2.close();
			}
		} finally {
			if (response != null) {
				response.close();
			}
			// 相当于关闭浏览器
			httpclient.close();
		}
	}

	private static void testUtil() throws IOException {
		String trainParams = "-s 3 -t 2 -c 100 -g 0.1 -p 0";
		String trainFilePath = "resources/zoulang_C_3_train_x_norm.data";
		String modelToSave = "resources/zoulang_C_3_SVR.model";

		LibSVMApi.svmTrain(trainParams, trainFilePath, modelToSave);

		String predicParams = "";
		String testPath = "resources/zoulang_C_3_test_x_norm.data";
		String outputPath = "resources/zoulang_C_3_SVR_x_output.data";
		LibSVMApi.svmPredict(predicParams, testPath, modelToSave, outputPath);
	}
}
