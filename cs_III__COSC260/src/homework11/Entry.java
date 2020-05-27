//TJ Liggett
//Homework 11
//Class Entry stores a key and a value.
//11_27_2018
//Entry.java


class Entry<K, V> implements Comparable<Entry<K,V>>{
	private K key;
	private V value;

	public Entry(K k, V v) {
		key = k;
		value = v;
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V v) {
		value = v;
	}

	public String toString() {
		return "Key=" + key + " Value=" + value;
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof Entry))
			return false;
		Entry<K, V> t = (Entry<K, V>) obj;
		return key.equals(t.getKey());
	}

	public int compareTo(Entry<K, V> obj) {
		return ((Comparable)key).compareTo(obj.getKey());
	}
}
