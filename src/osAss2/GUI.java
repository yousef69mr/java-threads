package osAss2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI implements ActionListener {
    private Network network;
    private JFrame frame;
    private  JPanel panel;
    private JLabel networkRun ,label1 ,label2;
    private JTextField connections ,numOfDevices;
    private ArrayList<JPanel> panels;
    private static int actions=0;

    GUI(){

        network =new Network(this);

        frame =new JFrame();
        frame.setSize(500,300);

        JButton firstButton =new JButton("Start");
        firstButton.setSize(100,50);
        firstButton.setBounds(10,20,80,30);
        firstButton.setBorderPainted(true);
     //   firstButton.setCursor(new Cursor(1));
        firstButton.addActionListener(this);

        networkRun =new JLabel("");
        networkRun.setBounds(10,80,80,25);


        label1 =new JLabel("What is the number of WI-FI Connections?");
        label1.setBounds(10,20,80,25);

        connections =new JTextField(10);

        label2 =new JLabel("What is the number of devices Clients want to connect?");
        label1.setBounds(10,20,80,25);

        numOfDevices =new JTextField(40);


        JPanel leftPanel=new JPanel();
        leftPanel.setLayout(new GridLayout(4,1));
        leftPanel.add(label1,BorderLayout.CENTER);
        leftPanel.add(connections);
        leftPanel.add(label2);
        leftPanel.add(numOfDevices);

        JPanel rightPanel=new JPanel();
        rightPanel.setLayout(new GridLayout(0,1));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
       // rightPanel.add(networkRun,BorderLayout.NORTH);
        rightPanel.add(firstButton,BorderLayout.CENTER);

        panel =new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        panel.setLayout(new GridLayout(0,2));

        panel.add(leftPanel,BorderLayout.EAST);
        panel.add(rightPanel,BorderLayout.WEST);



        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("GUI Title");
        frame.pack();
        frame.setVisible(true);


    }

    void createInputPanel(int number){
        JFrame inputFrame =new JFrame();
      //  inputFrame.setSize(1000,1000);
        inputFrame.setLayout(new GridLayout(0,1));
        panels =new ArrayList<JPanel>();
        for(int i=0;i<number;i++){

            JLabel nameLabel =new JLabel("Device Name : ");
            nameLabel.setBounds(10,20,80,25);

            JTextField  nameText =new JTextField();
            nameText.setBounds(5,30,80,80);

            JLabel typeLabel =new JLabel("Device Type : ");
            typeLabel.setBounds(10,20,80,25);

            JTextField  typeText =new JTextField();
            typeText.setBounds(5,30,80,80);

            JPanel inputPanel=new JPanel();
            inputPanel.setLayout(new GridLayout(0,2));
            inputPanel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
            inputPanel.add(nameLabel);
            inputPanel.add(nameText);
            inputPanel.add(typeLabel);
            inputPanel.add(typeText);


            panels.add(inputPanel);

            inputFrame.add(panels.get(i),BorderLayout.CENTER);
        }

        JButton button2 =new JButton("Run");
        button2.addActionListener(this);


        inputFrame.add(button2);
        inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inputFrame.setTitle("Input Devices Title");
        inputFrame.pack();
        inputFrame.setVisible(true);
    }




    void createRunPanel(){
        int i;

        // creating run tab
        JFrame runFrame =new JFrame();
        runFrame.setSize(1000,500);
        runFrame.setLayout(new GridLayout(0,1));
        ArrayList<JPanel> messages =new ArrayList<JPanel>();


        for (i=0;i<Integer.parseInt(numOfDevices.getText())*4+Integer.parseInt(numOfDevices.getText());i++){
            JLabel runLabel =new JLabel();
            JPanel runPanel =new JPanel();
            runPanel.add(runLabel);
           // m.setText("test");
            messages.add(runPanel);
            runFrame.add(runPanel,BorderLayout.CENTER);
        }





        runFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        runFrame.setTitle("Program Tab");
        runFrame.pack();
        runFrame.setVisible(true);

        /////////////////////////////////////////

        //run program
        for (i=0;i<Integer.parseInt(numOfDevices.getText());i++){
            System.out.println("/////////");
            //String input;
            String[] deviceData =new String[2];

            Component[] components= panels.get(i).getComponents();
           // Component component= panels.get(i).getComponent(1);
            System.out.println(" Device : "+((JTextField)components[1]).getText());
           // input = ((JTextField) components[1]).getText();
            deviceData[0]=((JTextField) components[1]).getText();
            deviceData[1]=((JTextField) components[3]).getText();
            /*for (j=0;j<2;j+=2) {
                if (components[j].getClass().equals(JTextField.class)) {
                    //list.add((JTextField)component);
                    input = ((JTextField) components[j]).getText();

                }
            }*/
            //  deviceData[j]=input;


            Device device =new Device(deviceData[0],deviceData[1],network.getRouter());
            network.getDevices().add(device);

        }


        for (i=0;i<network.getDevices().size();i++){
            network.getDevices().get(i).start();
        }

        int counter=0;


            while (counter<Integer.parseInt(numOfDevices.getText())*4+Integer.parseInt(numOfDevices.getText())){
                if(!network.getRouter().getMessages().isEmpty()){
                    Component[] messageComponents= messages.get(counter).getComponents();
                    ((JLabel)messageComponents[0]).setText(network.getRouter().getMessages().get(0));
                    ((JLabel)messageComponents[0]).updateUI();
                    System.out.println("/////"+network.getRouter().getMessages().get(0));

                    runFrame.pack();


                    network.getRouter().getMessages().remove(0);
                    try {
                        Thread.sleep(2000);
                    }catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    //((JLabel)messageComponents[0]).setText("#########################");
                    counter++;
                }

            }



    }

    Network getNetwork() {
        return  this.network;
    }
    boolean enteredBefore=false;
    @Override
    public void actionPerformed(ActionEvent e) {

        if(!connections.getText().isEmpty()&& enteredBefore==false) {
            network.createRouter(Integer.parseInt(connections.getText()));
            enteredBefore=true;
        }
        if(network.getRouter()!=null){
            System.out.println("Router assigned :)");
        }
        if(actions==0) {
            actions++;
            createInputPanel(Integer.parseInt(numOfDevices.getText()));
        }else if(actions==1){
            actions++;
            createRunPanel();
        }

    }

}
