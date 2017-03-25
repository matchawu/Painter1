import java.awt.*; //jlabel��
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList; //�e��

public class painter extends JFrame{
	private final JLabel statusBar;
	
	public painter() //�غc����
	{
		super("�p�e�a"); //JFrame�O�W�����@�� super�L �M�ᥴ�W��
		
		statusBar = new JLabel(); //private�O�ꪺ �ҥH�غc�����n���s�w�q�L�@��
		add(statusBar,BorderLayout.SOUTH);//BORDERLAYOUT �O�w�]��LAYOUT�ҥH����SET
		
		MouseHandler handler = new MouseHandler();
		PaintPanel paintpanel = new PaintPanel();
		
		add(paintpanel,BorderLayout.CENTER);//�o�̭n�gCENTER!!!
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
			statusBar.setText(String.format("��Ц�m:(%d,%d)",event.getX(),event.getY()));
		}
		
		
		@Override
		public void mousePressed( MouseEvent event)
		{
			statusBar.setText(String.format("��Ц�m: [%d,%d]",event.getX(),event.getY()));
		}
		
		@Override
		public void mouseReleased( MouseEvent event)
		{
			statusBar.setText(String.format("��Ц�m: [%d,%d]",event.getX(),event.getY()));
		}
		
		@Override
		public void mouseEntered( MouseEvent event)
		{
			statusBar.setText(String.format("��Ц�m: [%d,%d]",event.getX(),event.getY()));
		}
		
		@Override
		public void mouseExited( MouseEvent event)
		{
			statusBar.setText("�ƹ��b�e�����~");
		}
		
		@Override
		public void mouseDragged( MouseEvent event)
		{
			statusBar.setText(String.format("��Ц�m: [%d,%d]",event.getX(),event.getY()));
		}
		
		@Override
		public void mouseMoved( MouseEvent event)
		{
			statusBar.setText(String.format("��Ц�m: [%d,%d]",event.getX(),event.getY()));
		}
		
	}
	
	public class PaintPanel extends JPanel
	{
		private final ArrayList<Point> points = new ArrayList<>(); //�N�e���Q�����}�C ��arraylist�e�X�ӬO�@���I�@���I
		
		public PaintPanel()
		{
			addMouseMotionListener(
					new MouseMotionAdapter()
					{
						@Override
						public void mouseDragged(MouseEvent event)
						{
							points.add(event.getPoint()); //��y�Ш�points�̭�
							repaint(); //��s���O
						}
					});
		}
	
	
		@Override
		public void paintComponent(Graphics g) //graphics�O�e�ϭn�Ψ쪺 g�O�W��
		{
			super.paintComponent(g); //paintcomponent�Ographics�̭����@�ӪF��
			for (Point point : points)
				g.fillOval(point.x, point.y, 4, 4);//�]�w�e���j�p4.4
		}
	}
	
	public class ToolPanel extends JPanel
	{
		private final JLabel drawtool; //ø�Ϥu��
		private final JComboBox<String> pentool; //�U�Կ�� �̭������e�O�r�ꫬ�A
		private final String[] names = {"����","���u","����","�x��","�ꨤ�x��"};
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
			drawtool = new JLabel("[ø�Ϥu��]");
			add(drawtool);
			
			pentool = new JComboBox<String>(names);
			add(pentool);
			pentool.addItemListener(new ComboBoxButtomListener());
			
			pensize = new JLabel("[����j�p]");
			add(pensize);
			
			smallsize = new JRadioButton("�p",true);
			middlesize = new JRadioButton("��",false);
			bigsize = new JRadioButton("�j",false);
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
			
			full = new JCheckBox("��");
			add(full);
			full.addActionListener(listener);

			frontbutton = new JButton("�e����");
			backbutton = new JButton("�I����");
			clearbutton = new JButton("�M���e��");
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
			JOptionPane.showMessageDialog(null,  "�A�I��F : " + event.getActionCommand(),"�T��",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	class ComboBoxButtomListener implements ItemListener
	{
		public void itemStateChanged(ItemEvent event)
		{
			String string = (String) event.getItem();
			if(event.getStateChange() == ItemEvent.SELECTED)
			{
				JOptionPane.showMessageDialog(null,"�A�I��F : " + string, "�T��",JOptionPane.INFORMATION_MESSAGE);	
			}
		}
	}
	
	public static void main(String[] args)
	{
		painter test = new painter(); //�ź� ��ۤW�����W�r
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setSize(700,600);
		
		test.setLocationRelativeTo(null);
		test.setVisible(true);
	}
}
