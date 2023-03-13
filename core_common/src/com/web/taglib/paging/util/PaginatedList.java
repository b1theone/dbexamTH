package com.web.taglib.paging.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class PaginatedList<T> implements Serializable {

	/**
     *
     */
	private static final long serialVersionUID = 381166994202380723L;
	private Long totalRecord;
	private Long currentPage;
	private List<T> dataList;
	private String message;
	private boolean success;

	public PaginatedList() {
		super();
		dataList = new ArrayList<T>();
	}

	public Long getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(Long totalRecord) {
		this.totalRecord = totalRecord;
	}

	public Long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Long currentPage) {
		this.currentPage = currentPage;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void add(int index, T t) {
		dataList.add(index, t);
	}

	public boolean add(T t) {
		return dataList.add(t);
	}

	public boolean addAll(Collection<? extends T> c) {
		return dataList.addAll(c);
	}

	public boolean addAll(int index, Collection<? extends T> c) {
		return dataList.addAll(index, c);
	}

	public void clear() {
		dataList.clear();
	}

	public boolean contains(T t) {
		return dataList.contains(t);
	}

	public boolean containsAll(Collection<T> c) {
		return dataList.containsAll(c);
	}

	public T get(int index) {
		return dataList.get(index);
	}

	@Override
	public int hashCode() {
		return dataList.hashCode();
	}

	public int indexOf(T t) {
		return dataList.indexOf(t);
	}

	public boolean isEmpty() {
		return dataList.isEmpty();
	}

	public Iterator<T> iterator() {
		return dataList.iterator();
	}

	public int lastIndexOf(T o) {
		return dataList.lastIndexOf(o);
	}

	public ListIterator<T> listIterator() {
		return dataList.listIterator();
	}

	public ListIterator<T> listIterator(int index) {
		return dataList.listIterator(index);
	}

	public T remove(int index) {
		return dataList.remove(index);
	}

	public boolean remove(T t) {
		return dataList.remove(t);
	}

	public boolean removeAll(Collection<? extends T> c) {
		return dataList.removeAll(c);
	}

	public boolean retainAll(Collection<? extends T> c) {
		return dataList.retainAll(c);
	}

	public T set(int index, T t) {
		return dataList.set(index, t);
	}

	public int size() {
		return dataList.size();
	}

	public List<T> subList(int fromIndex, int toIndex) {
		return dataList.subList(fromIndex, toIndex);
	}

	public Object[] toArray() {
		return dataList.toArray();
	}

	@SuppressWarnings("hiding")
	public <T> T[] toArray(T[] a) {
		return dataList.toArray(a);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
}
