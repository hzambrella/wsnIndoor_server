package com.hz.wsnIndoorBack.libSVMUtils;

import java.io.IOException;
import java.util.Arrays;

public class LibSVMApi {
	/**
	 * svm训练
	 * 
	 * @param trainParams
	 *            libsvm的参数,具体见github。
	 * @param trainPath
	 *            训练数据文件相对路径
	 * @param modelToSavePath
	 *            模型文件保存的相对路径
	 * @throws IOException
	 */
	public static void svmTrain(String trainParams, String trainPath,
			String modelToSavePath) throws IOException {
		// 末尾加个空格，来拆分参数
		if (!trainParams.equals("")) {
			if (trainParams.charAt(trainParams.length() - 1) != ' ') {
				trainParams = trainParams + " ";
			}
		}

		String argStr = trainParams + trainPath + " " + modelToSavePath;
		String[] argv = argStr.split(" ");
		svm_train t = new svm_train();
		t.run(argv);
	}

	/**
	 * svm预测
	 * 
	 * @param predictParams
	 *            libsvm的参数,具体见github
	 * @param testPath
	 *            测试或者预测数据相对路径
	 * @param modelPath
	 *            模型文件保存的相对路径
	 * @param outputPath
	 *            结果文件的相对路径
	 * @throws IOException
	 */
	public static void svmPredict(String predictParams, String testPath,
			String modelPath, String outputPath) throws IOException {
		// 末尾加个空格，来拆分参数
		if (!predictParams.equals("")) {
			if (predictParams.charAt(predictParams.length() - 1) != ' ') {
				predictParams = predictParams + " ";
			}
		}

		String argStr = predictParams + testPath + " " + modelPath + " "
				+ outputPath;
		String[] argv = argStr.split(" ");
		System.out.println(Arrays.toString(argv));
		svm_predict.mainMethod(argv);
	}

	/**
	 * 归一化和反归一化
	 * @param operate 为true时是归一化，为false是反归一化
	 * @param value 输入
	 * @param lower 缩放到的区间最小值，一般为0
	 * @param upper 缩放到的区间最大值，一般为1
	 * @param minNum 数值最小值，原始区间
	 * @param maxNum 数值最大值，原始区间
	 * @return
	 */
	 	public static double svmScale(boolean operate, double value, double lower,
			double upper, double minNum, double maxNum) {
		if (operate){
			return ((value-minNum)*(upper-lower))/(maxNum-minNum)+lower;
			
		}else{
			return ((value-lower)*(maxNum-minNum))/(upper - lower)+minNum;
		}
	};
}
