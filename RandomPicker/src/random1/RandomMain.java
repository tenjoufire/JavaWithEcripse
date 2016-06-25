package random1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class RandomMain extends JFrame  implements ActionListener{

	JLabel title;
	JRadioButton kawakatsu;
	JRadioButton kondo;
	JLabel name;
	JButton pick;
	JTextField inText;
	JButton inB;

	InputStream is;
	OutputStream os;
	BufferedReader br;
	PrintWriter pw;

	ArrayList<String> member = new ArrayList<String>();
	String PickedName;

	RandomMain() throws IOException{

		//System.setProperty("file.encoding", "UTF-8");
		//memberリストの読み込み
		FileReader();

		//画面サイズの決定
		this.setSize(600,400);

		//ウインドウを閉じたらプログラム終了
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent ev){
				System.exit(0);
			}
		});

		getContentPane().setLayout(new BorderLayout());

		//タイトルテキスト設定
		title = new JLabel("テキトーに名前選ぶ何か",SwingConstants.CENTER);
		title.setFont(new Font("",Font.PLAIN,24));
		getContentPane().add(title, BorderLayout.NORTH);

		//選ばれた人の表示ラベル
		name = new JLabel("誰が選ばれるんですかね",SwingConstants.CENTER);
		//name.setPreferredSize(new Dimension(200,100));
		name.setFont(new Font("MeiryoUI",Font.PLAIN,38));
		getContentPane().add(name, BorderLayout.CENTER);

		//下のボタンたちをまとめて管理するやつ
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(500, 100));
		panel.setLayout(null);

		//picker
		pick = new JButton("Pick One!");
		pick.setBounds(0, 0, 100, 30);
		pick.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(e.getSource().equals(pick))
					picker();
			}
		});

		//川勝君選ばないボタン
		kawakatsu = new JRadioButton("川勝くんを選ばない", false);
		kawakatsu.setBounds(0, 40, 300, 30);
		kawakatsu.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				if(e.getStateChange()==ItemEvent.DESELECTED) addKawakatsu();
				else removeKawakatsu();
			}
		});

		//近藤君選ばないボタン
		kondo = new JRadioButton("近藤くんを選ばない", false);
		kondo.setBounds(0, 70, 300, 30);
		kondo.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				if(e.getStateChange()==ItemEvent.DESELECTED) addKondo();
				else removeKondo();
			}
		});

		//メンバー挿入フィールド
		inText = new JTextField(15);
		inText.setBounds(200, 0, 200, 30);

		//メンバー挿入ボタン
		inB = new JButton("Insert");
		inB.setBounds(400, 0, 100, 30);
		inB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(e.getSource().equals(inB))
					try {
						addMember();
					} catch (IOException e1) {
						// TODO 自動生成された catch ブロック
						e1.printStackTrace();
					}
			}
		});



		//panelにボタンを登録
		panel.add(pick);
		panel.add(kawakatsu);
		panel.add(kondo);
		panel.add(inText);
		panel.add(inB);
		this.getContentPane().add(panel, BorderLayout.SOUTH);

		this.setVisible(true);
	}

	//ランダムにひとり選ぶやつ
	public void picker(){
		//System.out.println(kon);
		//System.out.println(kawa);
		Random r = new Random();
		int i = r.nextInt(member.size());
		name.setText(member.get(i));

	}

	//ファイル読み込み
	public void FileReader() throws IOException{
		//URL url=this.getClass().getResource("memberList.txt");
		//File file = new File(url.getFile());
		//System.out.println(url.getFile());
		//System.out.println(url.getPath());
		is = getClass().getClassLoader().getResourceAsStream("memberList.txt");

		//BufferedReader br = new BufferedReader(new InputStreamReader( new FileInputStream(file),"UTF-8"));
		br = new BufferedReader(new InputStreamReader(is,"UTF-8"));

		String s;
		while((s = br.readLine()) != null)
			member.add(s);
		br.close();
	}

	//ファイルにメンバーを追加
	public void addMember() throws IOException{
		File file = new File("bin/memberList.txt");
		pw = new PrintWriter(new BufferedWriter(new FileWriter(file,true)));
		pw.println(inText.getText());
		member.add(inText.getText());
		pw.close();
	}

	public void addKawakatsu(){
		member.add("川勝くん");
	}

	public void addKondo(){
		member.add("近藤くん");
	}

	public void removeKawakatsu(){
		int i = member.indexOf("川勝くん");
		member.remove(i);
	}

	public void removeKondo(){
		int i = member.indexOf("近藤くん");
		member.remove(i);
	}



	public static void main(String[] args) throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		RandomMain meu = new RandomMain();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
