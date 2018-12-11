package libSVMUtils.dto;

import java.util.ArrayList;

public class HigherAnchorDTO {
	private ArrayList<Anchor>anchors;
	private int[][] hop;
	public ArrayList<Anchor> getAnchors() {
		return anchors;
	}
	public void setAnchors(ArrayList<Anchor> anchors) {
		this.anchors = anchors;
	}
	public int[][] getHop() {
		return hop;
	}
	public void setHop(int[][] hop) {
		this.hop = hop;
	}
}
