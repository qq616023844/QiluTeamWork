package unit;

public class User {
	private String nickname = null;//�ǳ�
    private String myPhone = null;
    private int creditScore = 0;//��������
	private double balance = 0;//���
	
	
    public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getMyPhone() {
		return myPhone;
	}
	public void setMyPhone(String myPhone) {
		this.myPhone = myPhone;
	}
	public int getCreditScore() {
		return creditScore;
	}
	public void setCreditScore(int creditScore) {
		this.creditScore = creditScore;
	}
	
}
