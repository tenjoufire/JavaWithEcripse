package yoshida;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class YoshidaFrame extends JFrame  implements ActionListener{

	public JButton yoshida;
	public JLabel title;
	public String ysd = "";


	YoshidaFrame(){
		//画面サイズの決定
		this.setSize(600,800);

		//ウインドウを閉じたらプログラム終了
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent ev){
				System.exit(0);
			}
		});

		getContentPane().setLayout(new BorderLayout());

		//タイトルテキスト設定
		title = new JLabel("YSD先輩",SwingConstants.CENTER);
		title.setFont(new Font("",Font.PLAIN,24));
		getContentPane().add(title, BorderLayout.NORTH);





		this.setVisible(true);
	}


	//YSDルーレット関数
	public void YsdRoulette(){
		int i = 1;
		ArrayList<String> ysdName = new ArrayList<String>();
		ysdName.add("Y"); ysdName.add("S"); ysdName.add("D");ysdName.add("F");ysdName.add("U");ysdName.add("N");

		while(true){
			Random r = new Random();
			int n = r.nextInt(ysdName.size());
			ysd += ysdName.get(n);
			if(ysd.length() > 3) ysd = ysd.substring(1, 4);
			System.out.print(ysdName.get(n));
			//System.out.println(" "+ysd);
			i++;
			if(i % 29 == 0) System.out.println("");
			if(ysd.equals("YSD")){
				System.out.println("何やってくれてるんだよっ！");
				break;
			}
			if(ysd.equals("SSD")){
				System.out.println("ううううああああああああああぁぁぁぁぁぁぁ！");
				break;
			}
			if(ysd.equals("FUN")){
				System.out.println("天空都市大学");
				break;
			}
			try{
				Thread.sleep(30); //3000ミリ秒Sleepする
				}catch(InterruptedException e){}

		}

	}


	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		YoshidaFrame meu = new YoshidaFrame();
		meu.YsdRoulette();

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
