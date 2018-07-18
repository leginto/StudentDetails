package in.leginto.studentstatus.client;


import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jetty.util.ajax.JSON;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dev.json.JsonValue;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
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
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.layout.client.Layout;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
//import com.google.gwt.user.client.rpc.core.java.util.Arrays;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
//import com.sun.org.apache.xpath.internal.operations.String;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import java_cup.parser;

public class StudentStatus implements EntryPoint {
	
	
	private static class Students
	{
		private final String sid;
		private final String name;
		private final String email;
		private final String mobile;
		
		public Students(String sid , String name, String email, String mobile)
		{
			this.sid = sid;
			this.name = name;
			this.email = email;
			this.mobile = mobile;
		}
		
		public String getSid()
		{
			return sid;
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
	
//	private static List<Students> STUDENTS = 
	
	private static final List<Students> STUDENTS = Arrays.asList();
			
			
			/*
			asList(new Students("STD0001","name", "chandfi", "841684354"),
			new Students("STD0002", "ndfge", "chandfi", "8548514354"),
			new Students("STD0003", "ndffewa", "sdfadfandfi", "8646684354"));
			*/

	public void onModuleLoad() {
		
		
		
		
		
		
		
		
		
		CellTable<Students> table = new CellTable<Students>();
		
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		
		
		
		
		
		
		
		// create 4 columns 
		TextColumn<Students> sidColumn = new TextColumn<StudentStatus.Students>() {

			@Override
			public String getValue(Students object) {
				// TODO Auto-generated method stub
				return object.getSid();
			}
		};
		
		TextColumn<Students> nameColumn = new TextColumn<StudentStatus.Students>() {

			@Override
			public String getValue(Students object) {
				// TODO Auto-generated method stub
				return object.getName();
			}
		};
		
		TextColumn<Students> emailColumn = new TextColumn<StudentStatus.Students>() {

			@Override
			public String getValue(Students object) {
				// TODO Auto-generated method stub
				return object.getEmail();
			}
		};
		
		TextColumn<Students> mobileColumn = new TextColumn<StudentStatus.Students>() {

			@Override
			public String getValue(Students object) {
				// TODO Auto-generated method stub
				return object.getMobile();
			}
		};
		
		table.addColumn(sidColumn,"Student ID");
		table.addColumn(nameColumn, "Name");
		table.addColumn(emailColumn, "E-mail");
		table.addColumn(mobileColumn, "Mobile Number");
		
		final ListDataProvider<Students> dataProvider = new ListDataProvider<Students>();
		
		dataProvider.addDataDisplay(table);
		
		
		final List<Students> list = dataProvider.getList();
		
		/*
		for(Students student : STUDENTS)
			list.add(student);
		*/
		
		// read data from json file and store in CellTable
		
		// to collect data from studentdata.json
		
		
		
		final RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET,"studentnames.json");
		
		
		try {
			requestBuilder.sendRequest(null, new RequestCallback() {
				
				@Override
				public void onResponseReceived(Request request, Response response) {
					// TODO Auto-generated method stub
					
					JSONArray jsonArray = (JSONArray) JSONParser.parseStrict(response.getText());
					
					for(int i=0; i<jsonArray.size(); i++)
					{
						JSONObject jsonObject = (JSONObject) jsonArray.get(i);
						list.add(new Students(
								jsonObject.get("sid").toString().replace("\"", ""), 
								jsonObject.get("sname").toString().replace("\"", ""), 
								jsonObject.get("semail").toString().replace("\"", ""), 
								jsonObject.get("smobile").toString().replace("\"", "")));
					}
					
					
					
				}
				
				@Override
				public void onError(Request request, Throwable exception) {
					// TODO Auto-generated method stub
					
				}
			});
		} catch (RequestException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		// dialog layout for add student
		
		
		HorizontalPanel horizPan1 = new HorizontalPanel();
		HorizontalPanel horizPan2 = new HorizontalPanel();
		HorizontalPanel horizPan3 = new HorizontalPanel();
		HorizontalPanel horizPan4 = new HorizontalPanel();
		
		
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
		
		nameText.addBlurHandler(new BlurHandler() {
			
			@Override
			public void onBlur(BlurEvent event) {
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
		emailText.addBlurHandler(new BlurHandler() {
			
			@Override
			public void onBlur(BlurEvent event) {
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
		
		mobileText.addBlurHandler(new BlurHandler() {
			
			@Override
			public void onBlur(BlurEvent event) {
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
		Button cancelButton = new Button("Cancel");
		final VerticalPanel vertiPan = new VerticalPanel();
		
		horizPan4.add(saveButton);
		horizPan4.add(cancelButton);
		
		
		
		vertiPan.add(horizPan1);
		vertiPan.add(horizPan2);
		vertiPan.add(horizPan3);
		vertiPan.add(horizPan4);
		
		
		
		
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
				
				if(nameText.getText().toString() != "Enter Name" && 
						emailText.getText().toString() != "email@example.com" && 
						mobileText.getText().toString()!= "Eg: 9999999999")
				{
					String newSid = ""+(list.size()+1);
					while(newSid.length()<3)
						newSid = "0"+newSid;
					
					
					
					list.add(new Students("STD"+newSid ,nameText.getText().toString(), 
							emailText.getText().toString(), 
							mobileText.getText().toString()));
					
					dataProvider.refresh();
					
				}
				
				
			}
		});
		
		cancelButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				addStuDialog.hide();
				
			}
		});
		
		
		
		RootPanel.get().add(mainMenu);
		RootPanel.get().add(table);
		
		
		
		
		
		
		
		
		
	
	
	}
}
