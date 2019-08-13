package com.pinkpaw.common;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.pinkpaw.animal.model.vo.ProtectedAnimal;

public class PAQuery {
	
	private static String getTagValue(String tag, Element eElement) {
		NodeList nlList = null;
		Node nValue = null;
		try {
	    nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
	    nValue = (Node) nlList.item(0);
		} catch(Exception e) {}
		
	    if(nValue == null) 
	        return null;
	    return nValue.getNodeValue();
	}

	public static void main(String[] args) {
		List<String[]> list = new ArrayList<String[]>();
		StringBuilder urlBuilder = null;
		
		SimpleDateFormat format  = new SimpleDateFormat("yyyyMMdd");
		
		Calendar cal = Calendar.getInstance();
		String date1 = format.format(cal.getTime());
		cal.add(Calendar.MONTH, -12);
		String date2 = format.format(cal.getTime());
		
		File file = new File("graph.sql");
        FileWriter writer = null;
		
		try {
			urlBuilder = new StringBuilder("http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic"); /*URL*/
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=M8qjyG4ZKlORR1wV3ZSNoafubF4A9B8LOwpNKHrOV4CxrLd4HNVJ5JUED7MspOV%2Fw1s2O0Htjnh%2Fr2VtnKfT%2Fw%3D%3D"); /*Service Key*/
			urlBuilder.append("&" + URLEncoder.encode("bgnde","UTF-8") + "=" + URLEncoder.encode(date2, "UTF-8")); /*유기날짜 (검색 시작일) (YYYYMMDD) */
	        urlBuilder.append("&" + URLEncoder.encode("endde","UTF-8") + "=" + URLEncoder.encode(date1, "UTF-8")); /*유기날짜 (검색 종료일) (YYYYMMDD) */
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("150000", "UTF-8")); /*페이지당 보여줄 개수*/
	        
	        DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			org.w3c.dom.Document doc = dBuilder.parse(urlBuilder.toString()); 

//			System.out.println(urlBuilder.toString());
			
			doc.getDocumentElement().normalize();
//			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("item");
			
			for(int temp = 0; temp < nList.getLength(); temp++){
				Node nNode = nList.item(temp);
				if(nNode.getNodeType() == Node.ELEMENT_NODE){
					
					Element eElement = (Element) nNode;
					String[] arr = new String[2];
					arr[0] = getTagValue("noticeEdt", eElement);
					arr[1] = getTagValue("processState", eElement);
					list.add(arr);
					writer = new FileWriter(file, true);
					writer.write("insert into graph values('"+arr[0]+"','"+arr[1]+"');\n");
					writer.flush();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            try {
                if(writer != null) writer.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

		//
//		System.out.println("디비 돌리기 시작!");
//		Runnable runnable = new Runnable() {
//			
//			int a = 0;
//			@Override
//			public void run() {
//				int num = a;
//				for(int i=num; i<num+50; i++) {
//					Connection conn = null;
//					PreparedStatement pstmt = null;
//					int result = 0;
//					String query = "insert into test1 values(?, ?)";
//					
//					try {
//						Class.forName("oracle.jdbc.driver.OracleDriver");
//						conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pinkpaw", "pinkpaw");
//						pstmt = conn.prepareStatement(query);
//						pstmt.setString(1, list.get(i)[0]);
//						pstmt.setString(2, list.get(i)[1]);
//						result = pstmt.executeUpdate();
//						++a;
//						System.out.println(i+"번째에요!");
//					} catch (Exception e) {
//						e.printStackTrace();
//					} finally {
//						try {
//							pstmt.close();
//							conn.close();
//						} catch (SQLException e) {
//							e.printStackTrace();
//						}
//					}
//				}
//			}
//		};
//
//		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
//
//		service.scheduleAtFixedRate(runnable, 0, 5, TimeUnit.SECONDS);
		
//		for(int i = 0; i<list.size(); i++) {
//			Connection conn = null;
//			PreparedStatement pstmt = null;
//			int result = 0;
//			String query = "insert into test1 values(?, ?)";
//
//			try {
//				Class.forName("oracle.jdbc.driver.OracleDriver");
//				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pinkpaw", "pinkpaw");
//				pstmt = conn.prepareStatement(query);
//				pstmt.setString(1, list.get(i)[0]);
//				pstmt.setString(2, list.get(i)[1]);
//				result = pstmt.executeUpdate();
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				try {
//					pstmt.close();
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
	}

}
