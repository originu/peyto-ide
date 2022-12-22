package peyto.ide.core.model;

import java.util.ArrayList;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class GroupModel {

	private String name;
	private ArrayList<ItemModel> items = new ArrayList<>();

	public GroupModel() {
	}

	public GroupModel(String name, ArrayList<ItemModel> items) {
		super();
		this.name = name;
		this.items = items;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<ItemModel> getItems() {
		return items;
	}

	public void setItems(ArrayList<ItemModel> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
