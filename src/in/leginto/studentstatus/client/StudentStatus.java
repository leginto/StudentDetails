package in.leginto.studentstatus.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.layout.client.Layout;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class StudentStatus implements EntryPoint {
	
	
	private static class Students
	{
		private final String name;
		private final String email;
		private final String mobile;
		
		public Students(String name, String email, String mobile)
		{
			this.name = name;
			this.email = email;
			this.mobile = mobile;
		}
		
		public String getName()
		{
			return name;
		}
		public String getEmail()
		{
			return email;
		}
		
		public String getMobile()
		{
			return mobile;
		}
		
		
	}
	
	
	

	public void onModuleLoad() {
		
		
		
		RequestBuilder requestBuild = new RequestBuilder(null,"studentdata.json");
		
		try {
			requestBuild.sendRequest(null, new RequestCallback() {
				
				@Override
				public void onResponseReceived(Request request, Response response) {
					// TODO Auto-generated method stub
					
					
				}
				
				@Override
				public void onError(Request request, Throwable exception) {
					// TODO Auto-generated method stub
					
				}
			});
		} catch (RequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		CellTable<Students> table = new CellTable<Students>();
		
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		
		
		
		// create 4 columns 
		
		
		
		// read data from json file and store in CellTable
		
		
		
		
		// dialog layout for add student
		
		
		HorizontalPanel horizPan1 = new HorizontalPanel();
		HorizontalPanel horizPan2 = new HorizontalPanel();
		HorizontalPanel horizPan3 = new HorizontalPanel();
		
		
		// text boxes
		
		final TextBox nameText = new TextBox();
		final TextBox emailText = new TextBox();
		final TextBox mobileText = new TextBox();
		

		
		nameText.addMouseDownHandler(new MouseDownHandler() {
			
			@Override
			public void onMouseDown(MouseDownEvent event) {
				// TODO Auto-generated method stub
				if(nameText.getText().equals("Enter Name"))
				{
					nameText.setText("");
				}
				
			}
		});
		
		nameText.addMouseOutHandler(new MouseOutHandler() {
			
			@Override
			public void onMouseOut(MouseOutEvent event) {
				// TODO Auto-generated method stub
				if(nameText.getText().length()==0)
				{
					nameText.setText("Enter Name");
				}
				
			}
		});
		//------------------
		emailText.addMouseDownHandler(new MouseDownHandler() {
			
			@Override
			public void onMouseDown(MouseDownEvent event) {
				// TODO Auto-generated method stub
				if(emailText.getText().equals("email@example.com"))
				{
					emailText.setText("");
				}
				
			}
		});
		
		emailText.addMouseOutHandler(new MouseOutHandler() {
			
			@Override
			public void onMouseOut(MouseOutEvent event) {
				// TODO Auto-generated method stub
				if(emailText.getText().length()==0)
				{
					emailText.setText("email@example.com");
				}
				
			}
		});
		//-----------------
		mobileText.addMouseDownHandler(new MouseDownHandler() {
			
			@Override
			public void onMouseDown(MouseDownEvent event) {
				// TODO Auto-generated method stub
				if(mobileText.getText().equals("Eg: 9999999999"))
				{
					mobileText.setText("");
				}
				
			}
		});
		
		mobileText.addMouseOutHandler(new MouseOutHandler() {
			
			@Override
			public void onMouseOut(MouseOutEvent event) {
				// TODO Auto-generated method stub
				if(mobileText.getText().length()==0)
				{
					mobileText.setText("Eg: 9999999999");
				}
				
			}
		});

		
		
		
		
		
		horizPan1.add(new Label(" \0 \0 Name: \0 \0  \0 \0  \0 \0  \0 \0  \0 \0 "));
		horizPan1.add(nameText);
		
		horizPan2.add(new Label(" \0 \0 Email Id:\0 \0  \0 \0 \0 \0 \0"));
		horizPan2.add(emailText);
		
		horizPan3.add(new Label(" \0 \0 Mobiel No: \0 \0  \0 \0 "));
		horizPan3.add(mobileText);
		
		
		// save button
		Button saveButton = new Button("Save");
		final VerticalPanel vertiPan = new VerticalPanel();
		
		vertiPan.add(horizPan1);
		vertiPan.add(horizPan2);
		vertiPan.add(horizPan3);
		vertiPan.add(saveButton);
		
		
		// add student command
		
		
		final DialogBox addStuDialog = new DialogBox();
		
		
		Command addStudent = new Command() {
			
			@Override
			public void execute() {
				// TODO Auto-generated method stub
				//Window.alert("Student added");
				
				// setting values
				
				nameText.setText("Enter Name");
				emailText.setText("email@example.com");
				mobileText.setText("Eg: 9999999999");
				
				
				
				//creating a dialog box
				
				addStuDialog.setText("Adding a new Student");
				addStuDialog.setGlassEnabled(true);
				addStuDialog.center();
				addStuDialog.add(vertiPan);
				addStuDialog.show();
				
				
				
			}
		};
		
		
		
		
		
		
		// view student command
		Command viewStudent = new Command() {
			
			@Override
			public void execute() {
				// TODO Auto-generated method stub
				Window.alert("View student");
			}
		};
		
		
		// created menu bar
		final MenuBar mainMenu = new MenuBar();
		
		mainMenu.addItem("Add Student",addStudent);
		mainMenu.addItem("View Student", viewStudent);
		
		
		
		// save button logic
		saveButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				addStuDialog.hide();
				
				// logic to add (name, email, mobile)to the existing record
				
				
				
				
				
			}
		});
		
		
		
		RootPanel.get().add(mainMenu);
		
		
		
		
		
		
		
		
		
		
	
	
	}
}
