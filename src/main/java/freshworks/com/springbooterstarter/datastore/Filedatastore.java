package freshworks.com.springbooterstarter.datastore;

public class Filedatastore implements CharSequence {
@Override
	public String toString() {
		return "Filedatastore [key=" + key + ", a=" + a + "]";
	}
String key;
int a;
public String getKey() {
	return key;
}
public void setKey(String key) {
	this.key = key;
}
public int getA() {
	return a;
}
public void setA(int a) {
	this.a = a;
}
public Filedatastore(String key, int a) {
	super();
	this.key = key;
	this.a = a;
}
public Filedatastore() {
	// TODO Auto-generated constructor stub
}
@Override
public char charAt(int index) {
	// TODO Auto-generated method stub
	return 0;
}
@Override
public int length() {
	// TODO Auto-generated method stub
	return 0;
}
@Override
public CharSequence subSequence(int start, int end) {
	// TODO Auto-generated method stub
	return null;
}

}
