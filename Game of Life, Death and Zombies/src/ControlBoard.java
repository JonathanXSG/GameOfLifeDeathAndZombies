import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControlBoard extends JPanel{
	private static final long serialVersionUID = 404911692099798724L;
	
	private String[] shapes={"dot","star","block","beehive","loaf","boat","tub","blinker","toad","beacon"};
	private JButton resetButton = new JButton("Reset");
	private JButton nextButton = new JButton("Next");
	private JButton refreshButton = new JButton("Refresh");
	private JButton backButton = new JButton("Back");
	private JButton startButton = new JButton("Start");
	private JButton stopButton = new JButton("Stop");
	private JSlider speedSlider = new JSlider(100, 1000, Main.initialTimerSpeed);
	private JComboBox<String> shapeComboBox = new JComboBox<String>(shapes);
	private static JLabel generationCount = new JLabel();
	
	private static JPanel gameboardControls = new JPanel(){
		private static final long serialVersionUID = 1056210011727406326L;
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(200, 250);
		}
	};
	private static JPanel gameboardSettings = new JPanel(){
		private static final long serialVersionUID = -5195869109910804768L;
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(200, 250);
		}
		@Override
		public void paintComponent(Graphics g) {
			generationCount.setText("Generation: "+Main.generationCount);
			super.paintComponent(g);
		}  
	};

	public ControlBoard(){
		setLayout(new BorderLayout());

		resetButton.addActionListener(reset);
		refreshButton.addActionListener(refresh);
		nextButton.addActionListener(next);
		startButton.addActionListener(start);
		stopButton.addActionListener(stop);
		speedSlider.setPaintTicks(true);
		speedSlider.setMinorTickSpacing(150);
		speedSlider.setMajorTickSpacing(150);
		speedSlider.setSnapToTicks(true);
		speedSlider.setPaintLabels(true);
		speedSlider.addChangeListener(timerSpeed);
		gameboardControls.add(resetButton);
		gameboardControls.add(refreshButton);
		gameboardControls.add(backButton);
		gameboardControls.add(nextButton);
		gameboardControls.add(Box.createRigidArea(new Dimension(200,10)));
		gameboardControls.add(speedSlider);
		gameboardControls.add(startButton);
		gameboardControls.add(stopButton);
		gameboardControls.add(Box.createRigidArea(new Dimension(200,10)));
		
		generationCount.setText("Generation: "+Main.generationCount);
		shapeComboBox.addActionListener(selectShape);
		gameboardSettings.add(generationCount);
		gameboardSettings.add(Box.createRigidArea(new Dimension(200,10)));
		gameboardSettings.add(shapeComboBox);

		add(gameboardControls,BorderLayout.PAGE_START);
		add(new JSeparator(SwingConstants.HORIZONTAL));
		add(gameboardSettings,BorderLayout.PAGE_END);
	}
	
	private ActionListener reset = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			Main.resetGameBoard();
			Main.repaintGameBoard();
			repaint();
		}
	};
	private ActionListener next = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			Main.runGameBoard();
		}
	};
	private ActionListener refresh = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			Main.repaintGameBoard();
			repaint();
		}
	};
	private ActionListener start = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			Main.startGameBoard();
		}
	};
	private ActionListener stop = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			Main.stopGameBoard();
		}
	};
	
	private ChangeListener timerSpeed = new ChangeListener() {
		@Override
		public void stateChanged(ChangeEvent evt) {
			JSlider slider = (JSlider) evt.getSource();
			if (!slider.getValueIsAdjusting()) {
				int value = slider.getValue();
				Main.stopGameBoard();
				Main.setTimerSpeed(value);
				Main.startGameBoard();
			}
		}
	};
	private ActionListener selectShape = new ActionListener() {
		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox<String> toggle = (JComboBox<String>) e.getSource();
			Main.setSelectedShape((String)toggle.getSelectedItem());
            
		}
	};
	public void rePaintGameboardSettings(){
		gameboardSettings.repaint();
	}
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(200, Frame.HEIGHT);
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}  
}
