package libSVMUtils;

import java.io.IOException;
import java.util.Arrays;

public class LibSVMApi {
	/**
	 * svmѵ��
	 * 
	 * @param trainParams
	 *            libsvm�Ĳ���,�����github��
	 * @param trainPath
	 *            ѵ�������ļ����·��
	 * @param modelToSavePath
	 *            ģ���ļ���������·��
	 * @throws IOException
	 */
	public static void svmTrain(String trainParams, String trainPath,
			String modelToSavePath) throws IOException {
		// ĩβ�Ӹ��ո�����ֲ���
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
	 * svmԤ��
	 * 
	 * @param predictParams
	 *            libsvm�Ĳ���,�����github
	 * @param testPath
	 *            ���Ի���Ԥ���������·��
	 * @param modelPath
	 *            ģ���ļ���������·��
	 * @param outputPath
	 *            ����ļ������·��
	 * @throws IOException
	 */
	public static void svmPredict(String predictParams, String testPath,
			String modelPath, String outputPath) throws IOException {
		// ĩβ�Ӹ��ո�����ֲ���
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
	 * 
	 * @param operate
	 * @param value
	 * @param lower
	 * @param upper
	 * @param minNum
	 * @param maxNum
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
