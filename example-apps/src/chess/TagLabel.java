package chess;

import javax.swing.JLabel;
public class TagLabel extends JLabel{
	private static final long serialVersionUID = -7496696142258573868L;
	TagLabel(String label)
	{
		super(label);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setVerticalAlignment(JLabel.CENTER);
	}
}

