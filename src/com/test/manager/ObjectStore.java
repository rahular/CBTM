package com.test.manager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ObjectStore {
	private ArrayList<Question> list = new ArrayList<Question>();

	public ArrayList<Question> getList() {
		return list;
	}

	void addQuestion(Question q) {
		list.add(q);
		storeData();
	}

	void storeData() {
		try {
			FileOutputStream fos=null;		
			fos = new FileOutputStream("questions.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public int readFromStore() {
		try {
			FileInputStream fis=null;
			fis=new FileInputStream("questions.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			list = (ArrayList<Question>) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return list.size();
	}

	void deleteQuestion(int index) {
		list.remove(index);
	}

	int getTotalQuestions() {
		return list.size();
	}

	void modifyQuestion(int index, Question q) {
		list.remove(index);
		list.add(index, q);
		storeData();
	}
}
