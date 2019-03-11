package com.hz.wsnIndoorBack.libSVMUtils.dto;

import java.util.ArrayList;

public class NormalAnchorDTO {
	private int[] anchorIds;
	private int[][] hop;
	public int[] getAnchorIds() {
		return anchorIds;
	}
	public void setAnchorIds(int[] anchorIds) {
		this.anchorIds = anchorIds;
	}
	public int[][] getHop() {
		return hop;
	}
	public void setHop(int[][] hop) {
		this.hop = hop;
	}
}
