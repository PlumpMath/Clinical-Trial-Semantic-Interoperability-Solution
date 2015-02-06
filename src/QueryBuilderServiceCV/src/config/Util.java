package config;

import java.util.ArrayList;

import queryBuilder.Triples;

public class Util {

	
	public static ArrayList<Triples> convertToArray (String classes, ArrayList<String> list) {

		ArrayList<Triples> res = new ArrayList<Triples>();
		int count =0;
		for (int i=0; i<list.size(); i++) {
			if (count==1) {
				Triples node = new Triples(classes, list.get(i-1), list.get(i));
				res.add(node);
				count=0;
			} else {
				count++;
			}
		}
		return res;
	}
	
	public static ArrayList<Triples> convertToArray (String classes, String[] list) {

		ArrayList<Triples> res = new ArrayList<Triples>();
		int count =0;
		for (int i=0; i<list.length; i++) {
			if (count==1) {
				Triples node = new Triples(classes, list[i-1], list[i]);
				res.add(node);
				count=0;
			} else {
				count++;
			}
		}
		return res;
	}
	
	public static ArrayList<Triples> convertToArray (String[] binding_list) {
		
		ArrayList<Triples> res = new ArrayList<Triples>();
		int count =0;
		for (int i=0; i<binding_list.length; i++) {
			if (count==2) {
				Triples node = new Triples(binding_list[i-2], binding_list[i-1], binding_list[i]);
				res.add(node);
				count=0;
			} else {
				count++;
			}
		}
		return res;
	}
	
	public static String[] convertFromArray (ArrayList<Triples> list) {
		
		ArrayList<String> res = new ArrayList<String>();
		for (int i=0;i<list.size();i++) {
			res.add(list.get(i).getClasses());
			res.add(list.get(i).getAttribute());
			res.add(list.get(i).getId());
		}
		String[] output = new String[res.size()];
		output = res.toArray(output);
		return output;
	}
}
