/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazebaby;

//import java.awt.Desktop.Action;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import javax.swing.Action;
import javax.swing.event.*;
import javax.swing.JComponent.*;
import java.awt.event.*;

import mazebaby.Keys.*;

import java.awt.*;
import java.util.Objects;


/**
 * Program :    Assignment 1: Application - Baby Maze
 * Filename:    BabyMaze.java
 * @author :    @CODEAUTHOR
 * Course  :    BEng/BSc/HND Computing Year 1
 * Module  :    CSY1020 ProblemSolving & Programming
 * Tutor   :    Gary Hill
 * @version:    2.0 Incorporates Artificial Intelligence!
 * Date    :    23/11/2021
 */

public class BabyMaze extends JFrame {
	
//	Action moveEast, moveWest, moveNorth, moveSouth;
    //Public variables declaration
    // Icons from Resource folder
    public static Icon babyLight = new ImageIcon(Objects.requireNonNull(BabyMaze.class.getClassLoader().getResource("icons/BabyLight.JPG")));
    public static Icon blanks = new ImageIcon(Objects.requireNonNull(BabyMaze.class.getClassLoader().getResource("icons/blankSpace.PNG")));
    public static Icon bricks = new ImageIcon(Objects.requireNonNull(BabyMaze.class.getClassLoader().getResource("icons/BrickWall.JPG")));
    public static Icon blob = new ImageIcon(Objects.requireNonNull(BabyMaze.class.getClassLoader().getResource("icons/blueBlob.JPG")));


    // Integer hashes(main memory locations) for the icons
    private static final int babyHash = babyLight.hashCode();
    private static final int blankHash = blanks.hashCode();

    public static JLabel[][] gridLabels = new JLabel[13][16];

    public static JPanel gameJPanel = new JPanel();

    public static boolean visibility=true;

    public static int index=0,rows, cols,delay=50;
    public static int[] oldAddress = new int[2];
    public static int[] newAddress = new int[2];
    public static int[] adjacentBabyAddress = new int[2];
    public static int[] currentBabyAddress = new int[2];

    //Private Variable declaration
    private final Panel controlsPanel = new Panel();
    private final JPanel buttonsPane = new JPanel();
    private final JPanel compassHolder = new JPanel();

    private static final JTextField MinutesTxtField = new JTextField();
    private static final JTextField hoursTextField = new JTextField();
    private static final JTextField SecondsTxtField = new JTextField();
    private static final JTextField currentSquareLocation = new JTextField();

    private static final JTextField directionTextBox = new JTextField();

    private final JTextField orientationTextBox = new JTextField();

    private final JButton actBtn = new JButton();
    private final JButton moveDownJbutton = new JButton();
    private final JButton moveDown_LeftJButton = new JButton();
    private final JButton moveDown_RightJButton = new JButton();
    private final JButton moveLeftJButton = new JButton();
    private final JButton moveRightJButton = new JButton();
    private final JButton moveUpJbutton = new JButton();
    private final JButton moveUp_LeftJButton = new JButton();
    private final JButton moveUp_RightJButton = new JButton();
    private final JButton exitBtn = new JButton();
    private final JButton resetGameBtn = new JButton();
    private final JButton runGameBtn = new JButton();
    private final JButton scrollCenterButton = new JButton();
    private final JButton toggleHorizontalBtn = new JButton();
    private final JButton toggleVerticalBtn = new JButton();

    private final JSeparator bottomSeparator = new JSeparator();

    private final JSlider speedVariationSlider = new JSlider(0,100,500,100);

    private static final JLabel compassLblImageHolder = new JLabel();
    private final JLabel controlsHeadingLbl = new JLabel();
    private final JLabel directionLbl = new JLabel();
    private static final JLabel currentSquareLabel = new JLabel();
    private final JLabel optionLbl = new JLabel();
    private final JLabel spacer1 = new JLabel();
    private final JLabel spacer2 = new JLabel();
    private final JLabel speedSliderLbl = new JLabel();

    private static String defaultOrientation="Vertical";
    private boolean sWatchStarted = false;
    // End of Variable declaration


    public BabyMaze()
    {
        initComponents();
    }

    private void initComponents(){
		Keys key = new Keys();
        initLabelsGrid(defaultOrientation);
        
		Keys.MoveEast moveEast = key.new MoveEast();
		Keys.MoveWest moveWest = key.new MoveWest();
		Keys.MoveSouth moveSouth = key.new MoveSouth();
		Keys.MoveNorth moveNorth = key.new MoveNorth();
		
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(825, 585));
        setResizable(false);
        setTitle("Plancks code lab Baby Maze");
        //setIconImage((Icon) blob);

        // This code sets style and Layout for game JPanel
        gameJPanel.setBackground(new Color(255, 255, 255));
        gameJPanel.setPreferredSize(new Dimension(500, 480));
        gameJPanel.setLayout(new GridLayout(13,16,-1,-1));

        buttonsPane.setBackground(new Color(204, 204, 204));

        spacer1.setFont(new Font("Tahoma", 1, 12));
        spacer1.setHorizontalAlignment(SwingConstants.CENTER);
        spacer1.setText(":");
        spacer1.setPreferredSize(new Dimension(30, 30));

        spacer2.setFont(new Font("Tahoma", 1, 12));
        spacer2.setHorizontalAlignment(SwingConstants.CENTER);
        spacer2.setText(":");
        spacer2.setPreferredSize(new Dimension(30, 30));

        controlsHeadingLbl.setFont(new Font("Tahoma", 1, 12));
        controlsHeadingLbl.setHorizontalAlignment(SwingConstants.CENTER);
        controlsHeadingLbl.setText("DIGITAL TIMER");

        optionLbl.setText("Option:");
        optionLbl.setPreferredSize(new Dimension(50, 20));

        hoursTextField.setEditable(false);
        hoursTextField.setBackground(new Color(0, 0, 0));
        hoursTextField.setForeground(new Color(255, 255, 255));
        hoursTextField.setHorizontalAlignment(JTextField.CENTER);
        hoursTextField.setText("00");
        hoursTextField.setPreferredSize(new Dimension(30, 30));

        MinutesTxtField.setEditable(false);
        MinutesTxtField.setBackground(new Color(0, 0, 0));
        MinutesTxtField.setForeground(new Color(255, 255, 255));
        MinutesTxtField.setHorizontalAlignment(JTextField.CENTER);
        MinutesTxtField.setText("00");
        MinutesTxtField.setPreferredSize(new Dimension(30, 30));

        SecondsTxtField.setEditable(false);
        SecondsTxtField.setBackground(new Color(0, 0, 0));
        SecondsTxtField.setForeground(new Color(255, 255, 255));
        SecondsTxtField.setHorizontalAlignment(JTextField.CENTER);
        SecondsTxtField.setText("00");
        SecondsTxtField.setPreferredSize(new Dimension(30, 30));
        orientationTextBox.setText("Vertical");
        orientationTextBox.setEditable(false);

        currentSquareLocation.setText("0");
        currentSquareLocation.setEditable(false);

        directionTextBox.setText("S");
        directionTextBox.setEditable(false);

        currentSquareLabel.setText("Square:");
        currentSquareLabel.setPreferredSize(new Dimension(50, 20));


        directionLbl.setText("Direction:");
        directionLbl.setPreferredSize(new Dimension(50, 20));

        controlsPanel.setBackground(new Color(204, 204, 204));

        compassLblImageHolder.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/Compass_North.JPG"))));

        GroupLayout compassHolderLayout = new GroupLayout(compassHolder);
        compassHolder.setLayout(compassHolderLayout);
        compassHolderLayout.setHorizontalGroup(
            compassHolderLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(compassLblImageHolder, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        compassHolderLayout.setVerticalGroup(
            compassHolderLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(compassLblImageHolder, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        moveRightJButton.setFont(new Font("Tahoma", 1, 12));
        moveRightJButton.setText(">");
        moveRightJButton.setMnemonic(KeyEvent.VK_RIGHT);
        moveRightJButton.setPreferredSize(new Dimension(50, 40));
        moveRightJButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                BabyMoveEast();
                if(defaultOrientation.equals("Vertical"))
                    printVerticalGridLabels(gridLabels);
                else
                    printHorizontalGridLabels(gridLabels);
            }
        });

        moveRightJButton.addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode()== KeyEvent.VK_DOWN)BabyMoveSouth();
        		else if(e.getKeyCode()== KeyEvent.VK_RIGHT)BabyMoveEast();
        		else if(e.getKeyCode()== KeyEvent.VK_LEFT)BabyMoveWest();
        		else if(e.getKeyCode()== KeyEvent.VK_UP)BabyMoveNorth();
        	  }
        });

        moveDownJbutton.setFont(new Font("Tahoma", 1, 12));
        moveDownJbutton.setText("v");
        moveDownJbutton.setMinimumSize(new Dimension(42, 23));
        moveDownJbutton.setPreferredSize(new Dimension(52, 42));
        moveDownJbutton.addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode()== KeyEvent.VK_DOWN)BabyMoveSouth();
        		else if(e.getKeyCode()== KeyEvent.VK_RIGHT)BabyMoveEast();
        		else if(e.getKeyCode()== KeyEvent.VK_LEFT)BabyMoveWest();
        		else if(e.getKeyCode()== KeyEvent.VK_UP)BabyMoveNorth();
        	}
        });
        moveDownJbutton.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent evt)
            {
                BabyMoveSouth();
                directionTextBox.setText(" S");
                compassLblImageHolder.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/Compass_South.JPG"))));
                currentSquareLocation.setText(getBabySquare());
                if(defaultOrientation.equals("Vertical"))
                    printVerticalGridLabels(gridLabels);
                else
                    printHorizontalGridLabels(gridLabels);
            }
        });

        moveLeftJButton.setFont(new Font("Tahoma", 1, 12));
        moveLeftJButton.setText("<");
        moveLeftJButton.setPreferredSize(new Dimension(50, 40));
		moveLeftJButton.getInputMap().put(KeyStroke.getKeyStroke("D"), "pressed");
		moveLeftJButton.addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode()== KeyEvent.VK_DOWN)BabyMoveSouth();
        		else if(e.getKeyCode()== KeyEvent.VK_RIGHT)BabyMoveEast();
        		else if(e.getKeyCode()== KeyEvent.VK_LEFT)BabyMoveWest();
        		else if(e.getKeyCode()== KeyEvent.VK_UP)BabyMoveNorth();        	   
        	  }
        });
        moveLeftJButton.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent evt)
            {
                BabyMoveWest();
                directionTextBox.setText(" W");
                compassLblImageHolder.setIcon(new ImageIcon(getClass().getResource("/icons/Compass_West.JPG")));
                currentSquareLocation.setText(getBabySquare());
                if(defaultOrientation.equals("Vertical"))
                    printVerticalGridLabels(gridLabels);
                else
                    printHorizontalGridLabels(gridLabels);
            }
        });
        moveUpJbutton.setFont(new Font("Tahoma", 1, 12));
        moveUpJbutton.setText("^");
        moveUpJbutton.setPreferredSize(new Dimension(50, 40));
        moveUpJbutton.addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode()== KeyEvent.VK_DOWN)BabyMoveSouth();
        		else if(e.getKeyCode()== KeyEvent.VK_RIGHT)BabyMoveEast();
        		else if(e.getKeyCode()== KeyEvent.VK_LEFT)BabyMoveWest();
        		else if(e.getKeyCode()== KeyEvent.VK_UP)BabyMoveNorth();
        	   
        	  }
        });
        moveUpJbutton.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent evt)
            {
                BabyMoveNorth();
                directionTextBox.setText(" N");
                compassLblImageHolder.setIcon(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/icons/Compass_North.JPG"))));
                currentSquareLocation.setText(getBabySquare());
                if(defaultOrientation.equals("Vertical"))
                    printVerticalGridLabels(gridLabels);
                else
                    printHorizontalGridLabels(gridLabels);
            }
        });

        moveDown_RightJButton.setFont(new Font("Tahoma", 1, 12));
        moveDown_RightJButton.setEnabled(false);
        moveDown_RightJButton.setPreferredSize(new Dimension(50, 40));

        scrollCenterButton.setFont(new Font("Tahoma", 1, 12));
        scrollCenterButton.setIcon(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/Icons/BabyDark.JPG"))));
        scrollCenterButton.setPreferredSize(new Dimension(54, 44));

        moveUp_LeftJButton.setFont(new Font("Tahoma", 1, 12));
        moveUp_LeftJButton.setEnabled(false);
        moveUp_LeftJButton.setPreferredSize(new Dimension(50, 40));

        moveUp_RightJButton.setFont(new Font("Tahoma", 1, 12));
        moveUp_RightJButton.setEnabled(false);
        moveUp_RightJButton.setPreferredSize(new Dimension(50, 40));

        moveDown_LeftJButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        moveDown_LeftJButton.setEnabled(false);
        moveDown_LeftJButton.setPreferredSize(new Dimension(50, 40));

        GroupLayout controlsPanelLayout = new GroupLayout(controlsPanel);
        controlsPanel.setLayout(controlsPanelLayout);
        controlsPanelLayout.setHorizontalGroup(
            controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(controlsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(moveLeftJButton, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
                        .addComponent(moveUp_LeftJButton, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
                    .addComponent(moveDown_LeftJButton, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(moveDownJbutton, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
                        .addComponent(moveUpJbutton, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrollCenterButton, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
                .addGroup(controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(controlsPanelLayout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(moveRightJButton, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
                            .addComponent(moveDown_RightJButton, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 39, Short.MAX_VALUE))
                    .addGroup(controlsPanelLayout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(moveUp_RightJButton, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(controlsPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(compassHolder, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        controlsPanelLayout.setVerticalGroup(
            controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, controlsPanelLayout.createSequentialGroup()
                .addGroup(controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(moveRightJButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGroup(controlsPanelLayout.createSequentialGroup()
                        .addGroup(controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(moveUpJbutton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(moveUp_LeftJButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(moveUp_RightJButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(moveLeftJButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(scrollCenterButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(moveDownJbutton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(moveDown_RightJButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(moveDown_LeftJButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(compassHolder, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        toggleVerticalBtn.setFont(new Font("Tahoma", 1, 11));
        toggleVerticalBtn.setText("Vertical");
        toggleVerticalBtn.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent evt)
            {
                defaultOrientation="Vertical";
                currentSquareLocation.setText("0");
                orientationTextBox.setText("Vertical");
                gameJPanel.removeAll();
                initLabelsGrid(defaultOrientation);
                gameJPanel.revalidate();
            }
        });

        toggleHorizontalBtn.setFont(new Font("Tahoma", 1, 11));
        toggleHorizontalBtn.setText("Horizontal");
        toggleHorizontalBtn.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent evt)
            {
                    defaultOrientation="Horizontal";
                    currentSquareLocation.setText("0");
                    orientationTextBox.setText("Horizontal");
                    gameJPanel.removeAll();
                    initLabelsGrid(defaultOrientation);
                    gameJPanel.revalidate();
            }
        });

        exitBtn.setFont(new Font("Tahoma", 1, 11));
        exitBtn.setText("Exit");
        exitBtn.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent evt)
            {
                System.exit(0);
            }
        });

        GroupLayout buttonsPaneLayout = new GroupLayout(buttonsPane);

        buttonsPane.setLayout(buttonsPaneLayout);
        buttonsPaneLayout.setHorizontalGroup(
            buttonsPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPaneLayout.createSequentialGroup()
                .addGroup(buttonsPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(buttonsPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(buttonsPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(buttonsPaneLayout.createSequentialGroup()
                                .addGroup(buttonsPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(optionLbl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(buttonsPaneLayout.createSequentialGroup()
                                        .addComponent(hoursTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(3, 3, 3)
                                        .addComponent(spacer1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(buttonsPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addGroup(buttonsPaneLayout.createSequentialGroup()
                                        .addComponent(MinutesTxtField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(spacer2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(SecondsTxtField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(buttonsPaneLayout.createSequentialGroup()
                                        .addComponent(orientationTextBox)
                                        .addGap(20, 20, 20))))
                            .addGroup(buttonsPaneLayout.createSequentialGroup()
                                .addGroup(buttonsPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(currentSquareLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(directionLbl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addGroup(buttonsPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(directionTextBox)
                                    .addComponent(currentSquareLocation, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)))
                            .addComponent(controlsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(buttonsPaneLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(buttonsPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(toggleHorizontalBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(toggleVerticalBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(exitBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(controlsHeadingLbl, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        buttonsPaneLayout.setVerticalGroup(
            buttonsPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(controlsHeadingLbl, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(buttonsPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(hoursTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(MinutesTxtField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(SecondsTxtField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(spacer1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(spacer2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(buttonsPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(optionLbl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(orientationTextBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(buttonsPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(currentSquareLocation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(currentSquareLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(buttonsPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(directionTextBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(directionLbl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(controlsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(toggleVerticalBtn, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(toggleHorizontalBtn)
                .addGap(5, 5, 5)
                .addComponent(exitBtn)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        actBtn.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/Act.JPG"))));
        actBtn.setText("Act");
        actBtn.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                babyAct();
            }
        });

        runGameBtn.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/Run.JPG"))));
        runGameBtn.setText("Run");
        runGameBtn.setPressedIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/Running.JPG"))));
        runGameBtn.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                simulatorStopWatch();
                babyActDelay();
            }
        });

        resetGameBtn.setIcon(new ImageIcon(getClass().getResource("/icons/Reset.JPG")));
        resetGameBtn.setText("Reset");
        resetGameBtn.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                flushGameJPanel();
            }
        });
		
//		Key Event Listeners/ Key bindings	
//		Action babyTurnEast = new AbstractAction() {
//		    private static final long serialVersionUID = 1L;
//
//			@Override
//		    public void actionPerformed(ActionEvent e) {
//				moveRightJButton.setText(">");
//		        BabyMoveEast();
//		    }
//		};
//		babyTurnEast.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("UP"));
//		moveRightJButton.setAction(babyTurnEast);
//		moveRightJButton.getActionMap().put("rightMove", babyTurnEast);
//		moveRightJButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
//			.put(KeyStroke.getKeyStroke("LEFT"), "babyTurnEast");
		
//		moveRightJButton.
		
		
		moveLeftJButton.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "leftMove");
		moveLeftJButton.getActionMap().put("leftMove", moveEast);
		
		moveUpJbutton.getInputMap().put(KeyStroke.getKeyStroke("UP"), "upMove");
		moveUpJbutton.getActionMap().put("upMove", moveEast);
		
		moveDownJbutton.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "downMove");
		moveDownJbutton.getActionMap().put("downMove", moveSouth);
		
        speedSliderLbl.setFont(new Font("Tahoma", 1, 11));
        speedSliderLbl.setHorizontalAlignment(SwingConstants.CENTER);
        speedSliderLbl.setText("Speed:");
        speedSliderLbl.setPreferredSize(new Dimension(46, 14));

        speedVariationSlider.setFont(new Font("Tahoma", 0, 10));
        speedVariationSlider.setOrientation(JSlider.HORIZONTAL);
        speedVariationSlider.setInverted(true);
        speedVariationSlider.setMinorTickSpacing(100);
        speedVariationSlider.setPaintTicks(true);
        speedVariationSlider.setSnapToTicks(true);
        speedVariationSlider.addChangeListener(ev -> delay = speedVariationSlider.getValue());

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(bottomSeparator)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, Short.MAX_VALUE)
                .addComponent(actBtn)
                .addGap(18, 18, 18)
                .addComponent(runGameBtn)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(resetGameBtn)
                .addGap(177, 177, 177)
                .addComponent(speedSliderLbl, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(speedVariationSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(gameJPanel, GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonsPane, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonsPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(gameJPanel, GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bottomSeparator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(speedSliderLbl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(actBtn)
                        .addComponent(runGameBtn)
                        .addComponent(resetGameBtn))
                    .addComponent(speedVariationSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        pack();
    }

    Timer stopwatch, babyWaiter;
    int sec=0,min=0,hrs=0;

    //This method simulates the movement of the baby across the maze
    public void simulatorStopWatch()
    {
        final int delayWait=1000;
        final int[][] babyCell = {new int[2]};
        if(!sWatchStarted){
            sWatchStarted = true;
            stopwatch = new Timer(delayWait, new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent actionEvent)
                {
                    babyCell[0] = getBabyAddress();
                    runGameBtn.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/Running.JPG"))));
                    if (sec > 59)
                    {
                        min += 1;
                        sec = 0;
                    }
                    if (min > 59)
                    {
                        hrs += 1;
                        min = 0;
                    }
                    SecondsTxtField.setText(String.format("%02d", sec));
                    MinutesTxtField.setText(String.format("%02d", min));
                    hoursTextField.setText(String.format("%02d", hrs));
                    sec += 1;

                    // This code checks if the baby has reached its destination and stops the timer
                    if ((babyCell[0][1] >= 16-1) && (defaultOrientation.equals("Vertical")) || (babyCell[0][0] >= 13-1) && (defaultOrientation.equals("Horizontal")))
                    {
                        sec=0;
                        min=0;
                        hrs=0;

                        runGameBtn.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/Run.JPG"))));
                        ((Timer)actionEvent.getSource()).stop();

                    }
                }
            });
            stopwatch.start();
        }
    }

    // This method delays the baby auto movement simulation according to the set speed on speed JSlider
    public void babyActDelay()
    {
        // Instantiating the javax.swing.Timer class
        babyWaiter = new Timer(delay, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                babyAct();
            }
        });
        babyWaiter.start();
    }

    // Initializing the gameJPanel Labels with baby icon, brick wall icons and blank spaces icons
    public static void initLabelsGrid(String orientation)
    {
        Random randy = new Random();
        int[] spaces = new int[16];
        int x = 0;
        int xp = 0;

        while(xp < 16)
        {
            spaces[xp] = randy.nextInt(13);
            System.out.println(spaces[xp]);
            xp++;
        }

        switch(orientation)
        {
            case "Vertical":
                for (rows = 0; rows < 13; rows++)
                {
                    int t = rows;
                    for (cols = 0; cols < 16; cols++)
                    {
                        if (cols == 0 && rows == 0)
                        {
                            gridLabels[rows][cols] = new JLabel(babyLight);
                            continue;
                        }
                        // Assigning white spaces(movable address) to the even indexed columns
                        if (cols % 2 == 0)
                        {
                            gridLabels[rows][cols] = new JLabel(blanks);

                        }
                        // Punching random spaces in the walls to allow the passage of baby
                        else if ((cols == 1 && rows == 8) || (cols == 3 && rows == 5) || (cols == 5 && rows == 12) || (cols == 7 && rows == 5)
                                || (cols == 9 && rows == 4) || (cols == 11 && rows == 10) || (cols == 13 && rows == 3) || (cols == 15 && rows == 0))
                        {
                            gridLabels[rows][cols] = new JLabel(blanks);
                        }
                        else
                            { gridLabels[rows][cols] = new JLabel(bricks);
                        }
                    }
                }
                getBabyAddress();
                printVerticalGridLabels(gridLabels);
                break;

            case "Horizontal":
                defaultOrientation="Horizontal";
                for(rows=0;rows<13;rows++)
                {
                    for(cols=0;cols<16;cols++)
                    {
                        if(cols==0 && rows == 0)
                        {
                            gridLabels[rows][cols] = new JLabel(babyLight);
                            continue;
                        }
                        // Creating blank spaces(The possible baby path)
                        if(rows%2==0)
                        {
                            gridLabels[rows][cols] = new JLabel(blanks);
                        }
                        // Punching random spaces in the walls to allow the passage of baby
                        else if((rows==1&&cols==8) || (rows==3&&cols==5) || (rows==5&&cols==12) || (rows==7&&cols==5)
                                || (rows==9&&cols==4) || (rows==11&&cols==10) || (rows==13&&cols==3) || (rows==15&&cols==0))
                        {
                            gridLabels[rows][cols] = new JLabel(blanks);
                        } else
                            {
                            gridLabels[rows][cols] = new JLabel(bricks);
                        }
                    }
                }
                getBabyAddress();
                printHorizontalGridLabels(gridLabels);
                break;
            }
        }


    // This method prints the 2D vertical array configuration of JLabel Icons on the Grid
    public static void printVerticalGridLabels(JLabel[][] grid)
    {

        for(rows=0;rows<13;rows++)
        {
            for(cols=0;cols<16;cols++)
            {
                gameJPanel.add(grid[rows][cols]);
            }

        }
    }
    //This method prints the 2D horizontal array configuration of JLabel icons on the Grid
    public static void printHorizontalGridLabels(JLabel[][] grid)
    {
        for(rows=0;rows<13;rows++)
        {
            for(cols=0;cols<16;cols++)
            {
                gameJPanel.add(grid[rows][cols]);
            }

        }
    }

    // This method returns the number of squares preceding the baby from array[0][0]
    public static String getBabySquare()
    {
        int[] babySquare = getBabyAddress();
        return  String.format("%02d",(((babySquare[0])*16)+babySquare[1]));
    }

    // This method is used to reset the gameJPanel and re-write the 2D labels array with the new Labels bearing new icons
    private static void flushGameJPanel()
    {
        SecondsTxtField.setText("00");
        MinutesTxtField.setText("00");
        hoursTextField.setText("00");

        currentSquareLocation.setText(getBabySquare());
        gameJPanel.removeAll();

        switch (defaultOrientation)
        {
            case "Vertical":
                initLabelsGrid("Vertical");
                break;
            case "Horizontal":
                initLabelsGrid("Horizontal");
                break;
            default:
                initLabelsGrid(defaultOrientation);
        }
        gameJPanel.revalidate();
    }

    // This method returns the 2D address of the baby in the maze in 1D format with index[0] as rows and index[1] as columns
    public static int[] getBabyAddress()
    {
        int[] arr = new int[2];
        int x, y;
        for(x =0; x <13; x++)
        {
            for (y = 0; y < 16; y++)
            {
                if (babyHash == (gridLabels[x][y].getIcon().hashCode()))
                {
                    arr[0] = x;
                    arr[1] = y;
                }
            }
        }
        return arr;
    }

    // This method moves the baby one move upwards
    public static void BabyMoveNorth()
    {
        oldAddress=getBabyAddress();
        newAddress[0] = oldAddress[0] - 1;
        newAddress[1] = oldAddress[1];
        if(blankHash == (gridLabels[oldAddress[0]-1][oldAddress[1]].getIcon().hashCode()))
        {
            gridLabels[oldAddress[0]][oldAddress[1]].setIcon(blanks);
            gridLabels[newAddress[0]][newAddress[1]].setIcon(babyLight);
        }
        directionTextBox.setText(" N");
        compassLblImageHolder.setIcon(new ImageIcon(Objects.requireNonNull(BabyMaze.class.getResource("/icons/Compass_North.JPG"))));
        currentSquareLocation.setText(getBabySquare());
    }

    // This method moves the baby one move to the left
    public static void BabyMoveWest()
    {
        oldAddress = getBabyAddress();
        newAddress[0] = oldAddress[0];
        newAddress[1] = oldAddress[1] - 1;
        if(blankHash == (gridLabels[oldAddress[0]][oldAddress[1]-1].getIcon().hashCode()))
        {
            gridLabels[oldAddress[0]][oldAddress[1]].setIcon(blanks);
            gridLabels[newAddress[0]][newAddress[1]].setIcon(babyLight);
        }
        directionTextBox.setText(" W");
        compassLblImageHolder.setIcon(new ImageIcon(BabyMaze.class.getResource("/icons/Compass_West.JPG")));
        currentSquareLocation.setText(getBabySquare());
    }

    // This method moves the baby one move to the left
    public static void BabyMoveEast()
    {
        oldAddress = getBabyAddress();
        newAddress[0] = oldAddress[0];
        newAddress[1] = oldAddress[1] + 1;
        if(blankHash == (gridLabels[oldAddress[0]][oldAddress[1]+1].getIcon().hashCode()))
        {
            gridLabels[oldAddress[0]][oldAddress[1]].setIcon(blanks);
            gridLabels[newAddress[0]][newAddress[1]].setIcon(babyLight);
        }
        directionTextBox.setText(" E");
        compassLblImageHolder.setIcon(new ImageIcon(BabyMaze.class.getResource("/icons/Compass_East.JPG")));
        currentSquareLocation.setText(getBabySquare());
    }

    // This method moves the baby one move bottom-wise
    public static void BabyMoveSouth()
    {
        oldAddress=getBabyAddress();
        newAddress[0] = oldAddress[0] + 1;
        newAddress[1] = oldAddress[1];
        if(blankHash == (gridLabels[oldAddress[0]+1][oldAddress[1]].getIcon().hashCode()))
        {
            gridLabels[oldAddress[0]][oldAddress[1]].setIcon(blanks);
            gridLabels[newAddress[0]][newAddress[1]].setIcon(babyLight);
        }
        directionTextBox.setText(" S");
        compassLblImageHolder.setIcon(new ImageIcon(BabyMaze.class.getResource("/icons/Compass_South.JPG")));
        currentSquareLocation.setText(getBabySquare());
    }

    //This method scans for spaces and moves the baby one step at a time on press of a button
    public void babyAct()
    {
        int index=0;
        int[] foundBlankOnPathAddress = new int[2];
        currentBabyAddress = getBabyAddress();

        switch (defaultOrientation) {
            case "Vertical":
                while (index < 13) {
                    // Assigning new address on cell to the right
                    adjacentBabyAddress[0] = currentBabyAddress[0];
                    adjacentBabyAddress[1] = currentBabyAddress[1] + 1;

                    // This code tests if a cell adjacent to the baby is a blank space
                    if (blankHash == (gridLabels[index][adjacentBabyAddress[1]].getIcon().hashCode())) {
                        foundBlankOnPathAddress[0] = index;
                        foundBlankOnPathAddress[1] = adjacentBabyAddress[1];
                        break;
                    }
                    index++;
                }
                if (currentBabyAddress[0] < (foundBlankOnPathAddress[0])) {
                    BabyMoveSouth();
                } else if (currentBabyAddress[0] > foundBlankOnPathAddress[0]) {
                    BabyMoveNorth();
                } else if (currentBabyAddress[0] == foundBlankOnPathAddress[0]) {
                    BabyMoveEast();
                }
                if (blankHash == (gridLabels[currentBabyAddress[0]][currentBabyAddress[1] + 1].getIcon().hashCode())) {
                    BabyMoveEast();
                }
            case "Horizontal":
                while (index < 16) {
                    // Assigning new address on cell to the cell below the baby
                    adjacentBabyAddress[0] = currentBabyAddress[0] + 1;
                    adjacentBabyAddress[1] = currentBabyAddress[1];

                    // This code tests if a cell adjacent to the baby is a blank space
                    if (blankHash == (gridLabels[adjacentBabyAddress[0]][index].getIcon().hashCode())) {
                        foundBlankOnPathAddress[0] = adjacentBabyAddress[0];
                        foundBlankOnPathAddress[1] = index;
                        break;
                    }
                    index++;
                }
                if (currentBabyAddress[1] < (foundBlankOnPathAddress[1])) {
                    BabyMoveEast();
                } else if (currentBabyAddress[1] == foundBlankOnPathAddress[1] - 1) {
                    BabyMoveWest();
                } else if (currentBabyAddress[1] > foundBlankOnPathAddress[1]) {
                    BabyMoveWest();
                }
                if (blankHash == (gridLabels[currentBabyAddress[0] + 1][currentBabyAddress[1]].getIcon().hashCode())) {
                    BabyMoveSouth();
                }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // Set the Nimbus look and feel
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(BabyMaze.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        // Create and display the form with the following lambda expression
        EventQueue.invokeLater(() -> new BabyMaze().setVisible(visibility));
    }

}
