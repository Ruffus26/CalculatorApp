import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Calculator {
     public static class CalculatorApp implements ActionListener, MouseListener {
         private final Dimension minWindowSize;

        //Constructor
        CalculatorApp(Dimension minSize){
            this.minWindowSize = minSize;
        }

        //Window
        private final Frame calc = new Frame("Calculator");

         //Window icon
         Image icon = Toolkit.getDefaultToolkit().getImage("./calcIcon.png");

        //Text Output
        JTextField out = new JTextField("0");

         //Unary Operators List
         ArrayList<String> unaryOp = new ArrayList<>(5);

         //Binary Operator List
         ArrayList<String> binaryOp = new ArrayList<>(4);

         //Colors
         Color equalBtnColor = new Color(70,105,0);
         Color digitBtnColor = new Color(29,30,34);
         Color deleteBtnColor = new Color(212,63,63);
         Color outBtnColor = new Color(51,66,82);

        //Window Listener on Close
        private void addExitAppListener(){
            calc.addWindowListener(new WindowAdapter() {
                 @Override
                 public void windowClosing(WindowEvent e) {
                     super.windowClosing(e);
                     calc.dispose();
                 }
             });
         }

        //Draw App
        private void createApp() throws IOException, FontFormatException {
            calc.setLayout(new BoxLayout(calc, BoxLayout.Y_AXIS));
            calc.setMinimumSize(minWindowSize);
            calc.setResizable(false);
            calc.setIconImage(icon);
            calc.setBackground(new Color(0,0,0));

            //Upload Custom Fonts
            //<---------------------------------------
            //Digital
            Font digitalFont = Font.createFont(Font.TRUETYPE_FONT, new File("./Digital Sans EF Medium.ttf")).deriveFont(Font.BOLD, 35);

            //Futura Light
            Font futuraFont = Font.createFont(Font.TRUETYPE_FONT, new File("./futura-light-italic-bt.ttf")).deriveFont(20f);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(digitalFont);
            ge.registerFont(futuraFont);
            //--------------------------------------->

            //Menu Top Bar
            MenuBar menuBar = new MenuBar();
            Menu file = new Menu("File");
            MenuItem exit = new MenuItem("Exit - Ctrl + X");
            file.add(exit);
            Menu edit = new Menu("Edit");
            MenuItem copy = new MenuItem("Copy - Ctrl + C");
            MenuItem paste = new MenuItem("Paste - Ctrl + V");
            edit.add(copy);
            edit.add(paste);
            Menu help = new Menu("Help");
            menuBar.add(file);
            menuBar.add(edit);
            menuBar.setHelpMenu(help);
            menuBar.setFont(futuraFont.deriveFont(Font.PLAIN, 12));

            //Components
            //<---------------------------------------
            //Output
            Dimension minSizeOut = new Dimension(400, 70);
            Dimension maxSizeOut = new Dimension(500, 70);
            out.setFont(digitalFont);
            out.setBackground(outBtnColor);
            out.setForeground(Color.white);
            out.setCursor(new Cursor(Cursor.TEXT_CURSOR));
            out.setHorizontalAlignment(SwingConstants.RIGHT);
            out.setMinimumSize(minSizeOut);
            out.setMaximumSize(maxSizeOut);
            out.setEditable(false);
            out.setFocusable(true);
            out.setBorder(null);

            //Character GRID
            Panel grid = new Panel();
            GridLayout table = new GridLayout(6, 4, 1, 1);
            grid.setLayout(table);
            Dimension minGridSize = new Dimension(400, 450);
            Dimension maxGridSize = new Dimension(500, 500);
            grid.setMinimumSize(minGridSize);
            grid.setMaximumSize(maxGridSize);

            //Buttons
            JButton percent = new JButton("%");
            JButton ce = new JButton("CE");
            JButton cancel = new JButton("C");
            JButton delete = new JButton("DEL");
            JButton inverse = new JButton("inv(x)");
            JButton power = new JButton("sqr(x)");
            JButton root = new JButton("sqrt(x)");
            JButton divide = new JButton("/");
            JButton seven = new JButton("7");
            JButton eight = new JButton("8");
            JButton nine = new JButton("9");
            JButton multiply = new JButton("x");
            JButton four = new JButton("4");
            JButton five = new JButton("5");
            JButton six = new JButton("6");
            JButton minus = new JButton("-");
            JButton one = new JButton("1");
            JButton two = new JButton("2");
            JButton three = new JButton("3");
            JButton plus = new JButton("+");
            JButton sign = new JButton("+/-");
            JButton zero = new JButton("0");
            JButton point = new JButton(".");
            JButton equal = new JButton("=");

            //Array of grid Buttons
            ArrayList<JButton> btnList = new ArrayList<>(24);

            btnList.add(percent);
            btnList.add(ce);
            btnList.add(cancel);
            btnList.add(delete);
            btnList.add(inverse);
            btnList.add(power);
            btnList.add(root);
            btnList.add(divide);
            btnList.add(seven);
            btnList.add(eight);
            btnList.add(nine);
            btnList.add(multiply);
            btnList.add(four);
            btnList.add(five);
            btnList.add(six);
            btnList.add(minus);
            btnList.add(one);
            btnList.add(two);
            btnList.add(three);
            btnList.add(plus);
            btnList.add(sign);
            btnList.add(zero);
            btnList.add(point);
            btnList.add(equal);

            //Add buttons to grid
            for(JButton button : btnList){
                grid.add(button);
            }

            //Change buttons design
            for(JButton button : btnList){
                button.setBorder(new LineBorder(digitBtnColor, 1));
                button.setFocusPainted(false);
                button.setForeground(Color.white);
                button.setBackground(digitBtnColor);
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                switch (button.getText()) {
                    case "inv(x)":
                    case "sqr(x)":
                    case "sqrt(x)":
                        button.setFont(futuraFont);
                        break;
                    case "DEL":
                        button.setBackground(deleteBtnColor);
                        button.setFont(digitalFont.deriveFont(Font.PLAIN, 20));
                        button.setBorder(new LineBorder(deleteBtnColor, 1));
                        break;
                    case "=":
                        button.setFont(digitalFont.deriveFont(Font.PLAIN, 30));
                        button.setBackground(equalBtnColor);
                        button.setBorder(new LineBorder(equalBtnColor, 1));
                        break;
                    default:
                        button.setFont(digitalFont.deriveFont(Font.PLAIN, 20));
                        break;
                }
            }
            //--------------------------------------->

            //Add components
            calc.setMenuBar(menuBar);
            calc.add(out);
            calc.add(grid);

            //Add Listener
            addExitAppListener();
            for(JButton button : btnList) {
                button.addActionListener(this);
                button.addMouseListener(this);
            }

            //Unary Operators List
            unaryOp.add("%");
            unaryOp.add("inv(x)");
            unaryOp.add("sqr(x)");
            unaryOp.add("sqrt(x)");
            unaryOp.add("+/-");

            //Binary Operators List
            binaryOp.add("+");
            binaryOp.add("-");
            binaryOp.add("x");
            binaryOp.add("/");

        }

        private void showApp(){
            calc.setVisible(true);
        }

         //Store current operator and operands
         String  firstOperand = "",
                 secondOperand = "",
                 operator = "";

         boolean wasPressed = false;

         @Override
         public void actionPerformed(ActionEvent actionEvent) {
             //Text of the pressed button
             String current = actionEvent.getActionCommand();

             out.setText(firstOperand + operator + secondOperand);

             //Verify button and apply actions
             out:
             if((current.charAt(0) >= '0' && current.charAt(0) <= '9') || (current.charAt(0) == '.')){
                //Digit
                 if(!operator.equals("")){
                     if(!secondOperand.equals("") && wasPressed){
                         secondOperand = "";
                         operator = "";
                         firstOperand = "";
                         if (current.equals(".")){
                             firstOperand = "0.";
                         }else {
                             firstOperand = current;
                         }
                         wasPressed = false;
                         out.setText(firstOperand + operator + secondOperand);
                         break out;
                     }
                     if(!secondOperand.contains(".")){
                         if(!((current.equals(".")) && secondOperand.equals(""))){
                             if(!((secondOperand.equals("0")) && (current.equals("0")))){
                                 if((secondOperand.equals("0") && !(current.equals(".")))){
                                     secondOperand = current;
                                 }else {
                                     secondOperand += current;
                                 }
                             }
                         }else {
                             secondOperand = "0.";
                         }
                     }else {
                         if(!(current.equals("."))){
                             secondOperand += current;
                         }
                     }
                 }else{
                     if((!firstOperand.equals("") && wasPressed) || (firstOperand.contains("NaN")) || firstOperand.contains("Infinity")){
                         if(current.equals(".")){
                             firstOperand = "0.";
                         }else{
                             firstOperand = current;
                         }
                         wasPressed = false;
                     } else {
                         if(!firstOperand.contains(".")){
                             if(!((current.equals(".")) && firstOperand.equals(""))){
                                 if(!((firstOperand.equals("0")) && (current.equals("0")))){
                                     if(firstOperand.equals("0") && !(current.equals("."))){
                                         firstOperand = current;
                                     }else {
                                         firstOperand += current;
                                     }
                                 }
                             }else {
                                 firstOperand = "0.";
                             }
                         }else {
                             if(!(current.equals("."))){
                                 firstOperand += current;
                             }
                         }
                     }
                 }
                 out.setText(firstOperand + operator + secondOperand);
             }else if(current.equals("CE")){
                //Clear last operand
                 if(!secondOperand.equals("")){
                     secondOperand = "";
                 }else{
                     firstOperand = "";
                     operator = "";
                 }
                 out.setText(firstOperand + operator + secondOperand);
             } else if(current.charAt(0) == 'C'){
                 //Clear the output
                 firstOperand = "";
                 secondOperand = "";
                 operator = "";
                 out.setText(firstOperand + operator + secondOperand);
             }else if(current.equals("DEL")){
                //Clear the last element
                if(!(firstOperand.equals("")) && operator.equals("")){
                    if(firstOperand.contains("NaN") || firstOperand.contains("Infinity")){
                        firstOperand = "";
                        out.setText(firstOperand + operator + secondOperand);
                    } else{
                        firstOperand = firstOperand.substring(0, firstOperand.length() - 1);
                        out.setText(firstOperand + operator + secondOperand);
                    }
                }else if(secondOperand.equals("")){
                    operator = "";
                    out.setText(firstOperand + operator + secondOperand);
                }else {
                    secondOperand = secondOperand.substring(0, secondOperand.length() - 1);
                    out.setText(firstOperand + operator + secondOperand);
                }
             }else if((current.equals("%")) || (current.equals("inv(x)")) || (current.equals("sqr(x)")) || (current.equals("sqrt(x)")) || (current.equals("+/-"))){
                //Unary Operators
                 // 0 - %
                 // 1 - inv(x)
                 // 2 - sqr(x)
                 // 3 - sqrt(x)
                 // 4 - +/-
                 wasPressed = true;
                 int c = unaryOp.indexOf(current);
                 double result = 0;
                 if(!(firstOperand.equals("")) && ((!firstOperand.contains("NaN")) || (!firstOperand.contains("Infinity")))){
                     if(operator.equals("")){
                         switch (c) {
                             case 0 : result = (Double.parseDouble(firstOperand) / 100);
                                      break;
                             case 1 : result = 1 / (Double.parseDouble(firstOperand));
                                      break;
                             case 2 : result = (Double.parseDouble(firstOperand) * Double.parseDouble(firstOperand));
                                      break;
                             case 3 : result = (Math.sqrt(Double.parseDouble(firstOperand)));
                                      break;
                             case 4 : result = -(Double.parseDouble(firstOperand));
                                      break;
                         }
                         firstOperand = Double.toString(result);
                         out.setText(firstOperand + operator + secondOperand);
                     }else if(!(secondOperand.equals(""))){
                         switch (c) {
                             case 0 : result = (Double.parseDouble(secondOperand) / 100);
                                      break;
                             case 1 : result = 1 / (Double.parseDouble(secondOperand));
                                      break;
                             case 2 : result = (Double.parseDouble(secondOperand) * Double.parseDouble(secondOperand));
                                      break;
                             case 3 : result = (Math.sqrt(Double.parseDouble(secondOperand)));
                                      break;
                             case 4 : result = Double.parseDouble(secondOperand);
                                 break;
                         }
                         secondOperand = Double.toString(result);
                         out.setText(firstOperand + operator + secondOperand);
                     }
                 }
             } else if(current.equals("=")){
                 //Equal
                 // 0 - +
                 // 1 - -
                 // 2 - x
                 // 3 - /
                 wasPressed = true;
                 if(firstOperand.contains("NaN") || firstOperand.contains("Infinity")){
                     firstOperand = "";
                     out.setText(firstOperand + operator + secondOperand);
                 } else {
                     if(!firstOperand.equals("")){
                         if(!operator.equals("") && secondOperand.equals("")){
                             operator = "";
                             out.setText(firstOperand + operator + secondOperand);
                         } else {
                             int c = binaryOp.indexOf(operator);
                             switch (c) {
                                 case 0 : firstOperand = Double.toString(Double.parseDouble(firstOperand) + Double.parseDouble(secondOperand));
                                     break;
                                 case 1 : firstOperand = Double.toString(Double.parseDouble(firstOperand) - Double.parseDouble(secondOperand));
                                     break;
                                 case 2 : firstOperand = Double.toString(Double.parseDouble(firstOperand) * Double.parseDouble(secondOperand));
                                     break;
                                 case 3 : firstOperand = Double.toString(Double.parseDouble(firstOperand) / Double.parseDouble(secondOperand));
                                     break;
                             }
                             operator = "";
                             secondOperand = "";
                             out.setText(firstOperand + operator + secondOperand);
                         }
                     }
                 }
             }else if(current.equals("+") || current.equals("-") || current.equals("x") || current.equals("/")){
                 //Binary Operators
                 if(firstOperand.equals("")){
                     if(current.equals("-")){
                         firstOperand = "-";
                         out.setText(firstOperand + operator + secondOperand);
                     }
                 } else if(firstOperand.contains("NaN") || (firstOperand.contains("Infinity"))){
                     firstOperand = "";
                     out.setText(firstOperand + operator + secondOperand);
                 }else{
                     if(secondOperand.equals("") && !firstOperand.equals("-")){
                         if(firstOperand.charAt(firstOperand.length() - 1) == '.'){firstOperand = firstOperand.substring(0, firstOperand.length() - 1);}
                         operator = current;
                         out.setText(firstOperand + operator + secondOperand);
                     }else if(!(operator.equals(""))){
                         int c = binaryOp.indexOf(operator);
                         switch (c) {
                             case 0 : firstOperand = Double.toString(Double.parseDouble(firstOperand) + Double.parseDouble(secondOperand));
                                 break;
                             case 1 : firstOperand = Double.toString(Double.parseDouble(firstOperand) - Double.parseDouble(secondOperand));
                                 break;
                             case 2 : firstOperand = Double.toString(Double.parseDouble(firstOperand) * Double.parseDouble(secondOperand));
                                 break;
                             case 3 : firstOperand = Double.toString(Double.parseDouble(firstOperand) / Double.parseDouble(secondOperand));
                                 break;
                         }
                         if(firstOperand.contains("Infinity") || firstOperand.contains("NaN")){
                             operator = "";
                         } else{
                             operator = current;
                         }
                         secondOperand = "";
                         out.setText(firstOperand + operator + secondOperand);
                     }
                 }
             }
         }

         @Override
         public void mouseClicked(MouseEvent mouseEvent) {}

         @Override
         public void mousePressed(MouseEvent mouseEvent) {}

         @Override
         public void mouseReleased(MouseEvent mouseEvent) {}

         @Override
         public void mouseEntered(MouseEvent mouseEvent) {
             Object source = mouseEvent.getSource();
             JButton Btn = null;

             if (source instanceof JButton){
                 Btn = (JButton)source;
             }

             assert Btn != null;
             Btn.setBackground(Btn.getBackground().brighter());
         }

         @Override
         public void mouseExited(MouseEvent mouseEvent) {
             Object source = mouseEvent.getSource();
             JButton Btn = null;

             if (source instanceof JButton){
                 Btn = (JButton)source;
             }

             assert Btn != null;

             if(Btn.getText().equals("DEL")){
                 Btn.setBackground(deleteBtnColor);
             }else if(Btn.getText().equals("=")){
                 Btn.setBackground(equalBtnColor);
             }else {
                 Btn.setBackground(digitBtnColor);
             }
         }
     }

    public static void main(String[] args) throws IOException, FontFormatException {
        //New Calculator App
        Dimension minSize = new Dimension(400, 500);
        CalculatorApp calculator = new CalculatorApp(minSize);

        //Create Calculator App
        calculator.createApp();

        //Show Calculator App
        calculator.showApp();
    }
}
