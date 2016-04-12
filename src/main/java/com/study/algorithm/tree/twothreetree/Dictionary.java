package com.study.algorithm.tree.twothreetree;

/** Source code example for "A Practical Introduction to Data
 Structures and Algorithm Analysis, 3rd Edition (Java)"
 by Clifford A. Shaffer
 Copyright 2008-2011 by Clifford A. Shaffer
 */

/** The Dictionary abstract class. */
public interface Dictionary<T> {

	/** Reinitialize dictionary */
	public void clear();

	/**
	 * Insert a record
	 * 
	 * @param k
	 *            The key for the record being inserted.
	 */
	public boolean insert(T k);

	public void print();

	/**
	 * Remove and return a record.
	 * 
	 * @param k
	 *            The key of the record to be removed.
	 * @return True if exists and removed. False otherwise
	 */
	public boolean remove(T k);

	/**
	 * Remove and return an arbitrary record from dictionary.
	 * 
	 * @return the record removed, or null if none exists.
	 */
	public T removeAny();

	/**
	 * @return A record matching "k" (null if none exists). If multiple records
	 *         match, return an arbitrary one.
	 * @param k
	 *            The key of the record to find
	 */
	public T find(T k);

	/** @return The number of records in the dictionary. */
	public int size();
};