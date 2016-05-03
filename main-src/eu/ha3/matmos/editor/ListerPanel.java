package eu.ha3.matmos.editor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import eu.ha3.matmos.editor.interfaces.ILister;

/*
--filenotes-placeholder
*/

@SuppressWarnings("serial")
public abstract class ListerPanel extends JPanel implements ILister
{
	private JLabel titleLabel;
	private JList<String> list;
	private JPanel panel;
	private JButton create;
	private JPanel panel_1;
	
	public ListerPanel()
	{
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane, BorderLayout.CENTER);
		
		this.list = new JList<String>();
		this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.list.setModel(new AbstractListModel<String>() {
			String[] values = new String[] {};
			
			@Override
			public int getSize()
			{
				return this.values.length;
			}
			
			@Override
			public String getElementAt(int index)
			{
				return this.values[index];
			}
		});
		scrollPane.setViewportView(this.list);
		
		this.panel = new JPanel();
		add(this.panel, BorderLayout.SOUTH);
		this.panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		this.create = new JButton("Create new");
		this.panel.add(this.create);
		
		this.panel_1 = new JPanel();
		add(this.panel_1, BorderLayout.NORTH);
		
		this.titleLabel = new JLabel("no label");
		this.panel_1.add(this.titleLabel);
		this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	@Override
	public void setTitle(String title)
	{
		this.titleLabel.setText(title);
	}
	
	protected void updateWith(Map<String, ?> listing)
	{
		this.list.removeAll();
		this.list.setListData(new ArrayList<String>(listing.keySet()).toArray(new String[listing.keySet().size()]));
	}
}
