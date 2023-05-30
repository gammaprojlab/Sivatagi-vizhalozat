package sivatagiVizhalozat;

import java.util.ArrayList;
import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DirectionSelect extends JDialog {
	
	ArrayList<FieldElement> connections;
	String[] io = new String[2];
	
	public DirectionSelect(ArrayList<FieldElement> con, Frame owner) {
		super(owner, "Input Dialog", true);
		connections = con;
		setTitle("Change Direction of Pump");
        setSize(400, 100);
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(Color.cyan);
        JPanel main = new JPanel(new BorderLayout());
        JPanel pCB = new JPanel(); pCB.setLayout(new BoxLayout(pCB, BoxLayout.X_AXIS));
        JPanel pFirst = new JPanel();
        JPanel pSecond = new JPanel();
        JPanel pButton = new JPanel(); pButton.setLayout(new BoxLayout(pButton, BoxLayout.X_AXIS));
        
        JComboBox<String> cbFirst = new JComboBox<String>();
        JComboBox<String> cbSecond = new JComboBox<String>();
        cbFirst.addItem("Close");
        cbSecond.addItem("Close");
        for(FieldElement f : connections) {
        	cbFirst.addItem(f.toString());
        	cbSecond.addItem(f.toString());
        }
        Dimension size = new Dimension(150,30);
        
        JPanel pOk = new JPanel(); pOk.setBorder(BorderFactory.createEmptyBorder(0, 45, 10, 5));
        JButton btOk = new JButton("Ok"); btOk.addActionListener(action -> {
        	io[0] = (String)cbFirst.getSelectedItem();
        	io[1] = (String)cbSecond.getSelectedItem();
        	if(io[0].equals("Close") || io[1].equals("Close")) {
        		io[0] = "Close";
        		io[1] = "Close";
        		this.dispose();
        	}
        	else if(!io[0].equals(io[1])) {
        		this.dispose();
        	}
        });
        btOk.setBackground(Color.GREEN);
        btOk.setPreferredSize(size);
        btOk.setMaximumSize(size);
        btOk.setFocusPainted(false);
        
        JPanel pCancel = new JPanel(); pCancel.setBorder(BorderFactory.createEmptyBorder(0, 5, 10, 45));
        JButton btCancel = new JButton("Cancel"); btCancel.addActionListener(action  -> {
        	io[0] = "Cancelled";
        	this.dispose();
        });
        btCancel.setBackground(Color.RED);
        btCancel.setPreferredSize(size);
        btCancel.setMaximumSize(size);
        btCancel.setFocusPainted(false);
        
        pFirst.add(cbFirst);
        pSecond.add(cbSecond);
        pCB.add(pFirst);
        pCB.add(pSecond);
        pOk.add(btOk);
        pButton.add(pOk);
        pCancel.add(btCancel);
        pButton.add(pCancel);
        
        JPanel filler = new JPanel();
        
        main.add(filler, BorderLayout.NORTH);
        main.add(pCB, BorderLayout.CENTER);
        main.add(pButton, BorderLayout.SOUTH);
        
        this.add(main);
	}
	
	public String[] ChangeDirection() {
		this.setVisible(true);
		
		return io;
	}
	
	
}
