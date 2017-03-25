import java.awt.*; //jlabel等
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList; //畫布

public class painter extends JFrame{
	private final JLabel statusBar;
	
	public painter() //建構城市
	{
		super("小畫家"); //JFrame是上面那一行 super他 然後打名稱
		
		statusBar = new JLabel(); //private是虛的 所以建構城市要重新定義他一次
		add(statusBar,BorderLayout.SOUTH);//BORDERLAYOUT 是預設的LAYOUT所以不用SET
		
		MouseHandler handler = new MouseHandler();
		PaintPanel paintpanel = new PaintPanel();
		
		add(paintpanel,BorderLayout.CENTER);//這裡要寫CENTER!!!
		paintpanel.setBackground(Color.white);
		paintpanel.addMouseListener(handler);		
		paintpanel.addMouseMotionListener(handler);
		
		
		ToolPanel toolpanel = new ToolPanel();
		add(toolpanel,BorderLayout.WEST);
		
	}
	
	private class MouseHandler implements MouseListener,MouseMotionListener

	{
		@Override
		public void mouseClicked(MouseEvent event)
		{
			statusBar.setText(String.format("游標位置:(%d,%d)",event.getX(),event.getY()));
		}
		
		
		@Override
		public void mousePressed( MouseEvent event)
		{
			statusBar.setText(String.format("游標位置: [%d,%d]",event.getX(),event.getY()));
		}
		
		@Override
		public void mouseReleased( MouseEvent event)
		{
			statusBar.setText(String.format("游標位置: [%d,%d]",event.getX(),event.getY()));
		}
		
		@Override
		public void mouseEntered( MouseEvent event)
		{
			statusBar.setText(String.format("游標位置: [%d,%d]",event.getX(),event.getY()));
		}
		
		@Override
		public void mouseExited( MouseEvent event)
		{
			statusBar.setText("滑鼠在畫布之外");
		}
		
		@Override
		public void mouseDragged( MouseEvent event)
		{
			statusBar.setText(String.format("游標位置: [%d,%d]",event.getX(),event.getY()));
		}
		
		@Override
		public void mouseMoved( MouseEvent event)
		{
			statusBar.setText(String.format("游標位置: [%d,%d]",event.getX(),event.getY()));
		}
		
	}
	
	public class PaintPanel extends JPanel
	{
		private final ArrayList<Point> points = new ArrayList<>(); //將畫布想像成陣列 用arraylist畫出來是一個點一個點
		
		public PaintPanel()
		{
			addMouseMotionListener(
					new MouseMotionAdapter()
					{
						@Override
						public void mouseDragged(MouseEvent event)
						{
							points.add(event.getPoint()); //抓座標到points裡面
							repaint(); //刷新面板
						}
					});
		}
	
	
		@Override
		public void paintComponent(Graphics g) //graphics是畫圖要用到的 g是名稱
		{
			super.paintComponent(g); //paintcomponent是graphics裡面的一個東西
			for (Point point : points)
				g.fillOval(point.x, point.y, 4, 4);//設定畫筆大小4.4
		}
	}
	
	public class ToolPanel extends JPanel
	{
		private final JLabel drawtool; //繪圖工具
		private final JComboBox<String> pentool; //下拉選單 裡面的內容是字串型態
		private final String[] names = {"筆刷","直線","橢圓形","矩形","圓角矩形"};
		private final JLabel pensize;
		private final JRadioButton smallsize;
		private final JRadioButton middlesize;
		private final JRadioButton bigsize;
		private final ButtonGroup buttongroup;
		private final JCheckBox full;
		private final JButton frontbutton;
		private final JButton backbutton;
		private final JButton clearbutton;
		
		MessageListener listener = new MessageListener();
		
		public ToolPanel()
		{
			setLayout(new GridLayout(10,1));
			drawtool = new JLabel("[繪圖工具]");
			add(drawtool);
			
			pentool = new JComboBox<String>(names);
			add(pentool);
			pentool.addItemListener(new ComboBoxButtomListener());
			
			pensize = new JLabel("[筆刷大小]");
			add(pensize);
			
			smallsize = new JRadioButton("小",true);
			middlesize = new JRadioButton("中",false);
			bigsize = new JRadioButton("大",false);
			buttongroup = new ButtonGroup();
			add(smallsize);
			add(middlesize);
			add(bigsize);
			buttongroup.add(smallsize);
			buttongroup.add(middlesize);
			buttongroup.add(bigsize);
			smallsize.addActionListener(listener);
			middlesize.addActionListener(listener);
			bigsize.addActionListener(listener);
			
			full = new JCheckBox("填滿");
			add(full);
			full.addActionListener(listener);

			frontbutton = new JButton("前景色");
			backbutton = new JButton("背景色");
			clearbutton = new JButton("清除畫面");
			add(frontbutton);
			add(backbutton);
			add(clearbutton);
			frontbutton.addActionListener(listener);
			backbutton.addActionListener(listener);
			clearbutton.addActionListener(listener);
			
				
		}
		
	

	}

	class MessageListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			JOptionPane.showMessageDialog(null,  "你點選了 : " + event.getActionCommand(),"訊息",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	class ComboBoxButtomListener implements ItemListener
	{
		public void itemStateChanged(ItemEvent event)
		{
			String string = (String) event.getItem();
			if(event.getStateChange() == ItemEvent.SELECTED)
			{
				JOptionPane.showMessageDialog(null,"你點選了 : " + string, "訊息",JOptionPane.INFORMATION_MESSAGE);	
			}
		}
	}
	
	public static void main(String[] args)
	{
		painter test = new painter(); //藍綠 跟著上面的名字
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setSize(700,600);
		
		test.setLocationRelativeTo(null);
		test.setVisible(true);
	}
}
