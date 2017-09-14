
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
class Calculator extends JFrame implements ActionListener
{
	private ArrayList<JButton> buttonsA;
	private JTextField display;
	private String operator;
	private double runningTotal;
	private String textBox;
	/**
	 *creates a calculator object that the user interacts with
	 */
	public Calculator()
	{
		
		setSize(450,450);

		setTitle("CALCULATOR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buttonsA=new ArrayList<JButton>();
		makeButtons();
		//setResizable(false);
		display = new JTextField();
		display.setEditable(false);
		display.setHorizontalAlignment(JTextField.RIGHT);
		display.setPreferredSize(new Dimension(450,50));
		Font font = new Font(Font.DIALOG,Font.BOLD,26);
		display.setFont(font);
		textBox="";
		operator="";
		JPanel buttons = new JPanel();
		setLayout(new BorderLayout());
		add(display,BorderLayout.NORTH);
		buttons.setLayout(new GridLayout(5,5,5,5));
		for(int i = 0;i<25;i++)buttons.add(buttonsA.get(i));
		add(buttons,BorderLayout.CENTER);
				setVisible(true);
				
	}

	/**
	 *creates the responses the user experiences when the user presses a button
	 *@param e the action performed by the user 
	 */
	public void actionPerformed(ActionEvent e)
	{
		JButton button = (JButton)e.getSource();
		if(isNumber(button))
		{
			textBox+=button.getText();
			display.setText(textBox);
		}
	else	if(button.getText().equals("."))
		{
			if(!textBox.contains("."))textBox+=".";
			display.setText(textBox);
		}
	else	if(button.getText().equals("Clr"))
		{
		runningTotal=0;
		textBox="";
			display.setText(textBox);
		}
else		if(button.getText().equals("CE"))
		{
			textBox="";
			display.setText(textBox);
		}
	else	if(isOperator(button)&&!display.getText().equals("")){
	
			if(!operator.equals(""))
			{
			double output = 0;
			if(textBox.equals(""))textBox=display.getText();
			switch(operator)
			{
				case "+":output=runningTotal+Double.parseDouble(textBox);break;
				case "-":output=runningTotal-Double.parseDouble(textBox);break;
				case "*":output=runningTotal*Double.parseDouble(textBox);break;
				case "/":output=runningTotal/Double.parseDouble(textBox);break;
			}
			textBox="";
			display.setText(output+"");
			operator="";
			runningTotal=output;
				operator=button.getText();
			}	
			else{
			operator=button.getText();
			if(textBox.equals(""))textBox=display.getText();
			runningTotal=Double.parseDouble(textBox);
			textBox="";
			display.setText("");
			}
		}
	else	if(button.getText().equals("=")&&!operator.equals(""))
		{	double output = 0;
			if(textBox.equals(""))textBox=display.getText();
			switch(operator)
			{
				case "+":output=runningTotal+Double.parseDouble(textBox);break;
				case "-":output=runningTotal-Double.parseDouble(textBox);break;
				case "*":output=runningTotal*Double.parseDouble(textBox);break;
				case "/":output=runningTotal/Double.parseDouble(textBox);break;
			}
			textBox="";
			display.setText(output+"");
			operator="";
			runningTotal=output;
		}

	else	if(button.getText().equals("Sqrt")&&!display.getText().equals(""))
		{
			textBox=display.getText();
			runningTotal=Math.sqrt(Double.parseDouble(textBox));
			textBox="";	
			display.setText(runningTotal+"");	
		}	
	else	if(button.getText().equals("%")&&!display.getText().equals(""))
		{
			double output = 0;
			double percent = Double.parseDouble(textBox)/100*runningTotal;
			switch(operator)
			{
				case "+":output=runningTotal+percent;break;
				case "-":output=runningTotal-percent;break;
				case "*":output=runningTotal*percent;break;
				case "/":output=runningTotal/percent;break;
			}
			textBox="";
			display.setText(output+"");
			operator="";
			runningTotal=output;
		}
		if(isTrig(button)&&!display.getText().equals(""))
		{
			textBox=display.getText();
			switch(button.getText())
			{
				case "sin":	runningTotal=Math.sin(Double.parseDouble(textBox));break;
				case "cos":	runningTotal=Math.cos(Double.parseDouble(textBox));break;
				case "tan":	runningTotal=Math.tan(Double.parseDouble(textBox));break;
			}
			textBox="";	
			display.setText(runningTotal+"");	
		}
		if(button.getText().equals("PI"))
		{
			textBox=Math.PI+"";
			display.setText(textBox);
		}
		if(button.getText().equals("abs")&&!display.getText().equals(""))
		{
		textBox=display.getText();
			runningTotal=Math.abs(Double.parseDouble(textBox));
			textBox="";	
			display.setText(runningTotal+"");
		}
				
	}
	/**
	 *makes the buttons for the calculator
	 */
	public void makeButtons()
	{
		buttonsA.add(new JButton("7"));//0
		buttonsA.add(new JButton("8"));//1
		buttonsA.add(new JButton("9"));//2
		buttonsA.add(new JButton("/"));//3
		buttonsA.add(new JButton("Sqrt"));//4
		buttonsA.add(new JButton("4"));//5
		buttonsA.add(new JButton("5"));//6
		buttonsA.add(new JButton("6"));//7
		buttonsA.add(new JButton("*"));//8
		buttonsA.add(new JButton("CE"));//9
		buttonsA.add(new JButton("1"));//10
		buttonsA.add(new JButton("2"));//11
		buttonsA.add(new JButton("3"));//12
		buttonsA.add(new JButton("-"));//13
		buttonsA.add(new JButton("Clr"));//14
		buttonsA.add(new JButton("0"));//15
		buttonsA.add(new JButton("."));//16
		buttonsA.add(new JButton("%"));//17
		buttonsA.add(new JButton("+"));//18
		buttonsA.add(new JButton("="));//19
		buttonsA.add(new JButton("PI"));//20
		buttonsA.add(new JButton("sin"));//21
		buttonsA.add(new JButton("cos"));//22
		buttonsA.add(new JButton("tan"));//23
		buttonsA.add(new JButton("abs"));//24
		for(JButton b: buttonsA)
		{b.addActionListener(this);
		b.setForeground(Color.RED);
		b.setBackground(Color.WHITE);
		}
	}
	/**
	 *checks if the button is an operator
	 *@param Button the button to check
	 *@return if the button is an operator
	 */
	public boolean isOperator(JButton button)
	{
		return (button.getText().equals("/")||button.getText().equals("-")||button.getText().equals("+")||button.getText().equals("*"));
	}
	/**
	 *checks if the button is an number
	 *@param Button the button to check
	 *@return if the button is an number
	 */
	public boolean isNumber(JButton button)
	{
		return Character.isDigit(button.getText().charAt(0));
	}
		/**
	 *checks if the button is a trig function
	 *@param Button the button to check
	 *@return if the button is a trig function
	 */
	public boolean isTrig(JButton button)
	{
		return (button.getText().equals("sin")||button.getText().equals("cos")||button.getText().equals("tan"));
	}
	/**
	 *makes a calculator for interaction
	 */
		public static void main(String args[])
	{
		Calculator calc  = new Calculator();
	}
}