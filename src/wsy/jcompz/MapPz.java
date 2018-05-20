package wsy.jcompz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;

import wsy.dao.Dao;
import wsy.model.BookType;

public class MapPz {
	static Map map = new HashMap();

	public static Map getMap() {
		List list = Dao.selectBookType();
		for (int i = 0; i < list.size(); i++) {
			BookType booktype = (BookType) list.get(i);

			Item item = new Item();
			item.setId(booktype.gettypeId());
			item.setName(booktype.getTypeName());
			map.put(item.getId(), item);

		}
		return map;
	}
}
