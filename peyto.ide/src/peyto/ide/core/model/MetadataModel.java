package peyto.ide.core.model;

import java.util.ArrayList;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class MetadataModel {

	private String kind;
	private ArrayList<String> input = new ArrayList<>();
	private ArrayList<GroupModel> groups = new ArrayList<>();

	public MetadataModel() {
	}

	public MetadataModel(String kind, ArrayList<String> input, ArrayList<GroupModel> groups) {
		this.kind = kind;
		this.input = input;
		this.groups = groups;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public ArrayList<String> getInput() {
		return input;
	}

	public void setInput(ArrayList<String> input) {
		this.input = input;
	}

	public ArrayList<GroupModel> getGroups() {
		return groups;
	}

	public void setGroups(ArrayList<GroupModel> groups) {
		this.groups = groups;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
