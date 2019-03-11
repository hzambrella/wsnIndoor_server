package com.hz.wsnIndoorBack.libSVMUtils.dto;

import java.util.ArrayList;

public class HigherAnchorDTO {
	private ArrayList<AnchorData>anchors;
	private int[][] hop;
	public ArrayList<AnchorData> getAnchors() {
		return anchors;
	}
	public void setAnchors(ArrayList<AnchorData> anchors) {
		this.anchors = anchors;
	}
	public int[][] getHop() {
		return hop;
	}
	public void setHop(int[][] hop) {
		this.hop = hop;
	}
}
