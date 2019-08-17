package com.pinkpaw.animal.model.dao;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.sun.xml.internal.txw2.Document;

import java.io.BufferedReader;
import java.io.IOException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class ApiExplorer {
	
	private static String getTagValue(String tag, Element eElement) {
	    NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
	    Node nValue = (Node) nlList.item(0);
	    if(nValue == null) 
	        return null;
	    return nValue.getNodeValue();
	}

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=M8qjyG4ZKlORR1wV3ZSNoafubF4A9B8LOwpNKHrOV4CxrLd4HNVJ5JUED7MspOV%2Fw1s2O0Htjnh%2Fr2VtnKfT%2Fw%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("bgnde","UTF-8") + "=" + URLEncoder.encode("20190701", "UTF-8")); /*유기날짜 (검색 시작일) (YYYYMMDD) */
        urlBuilder.append("&" + URLEncoder.encode("endde","UTF-8") + "=" + URLEncoder.encode("20190729", "UTF-8")); /*유기날짜 (검색 종료일) (YYYYMMDD) */
//        urlBuilder.append("&" + URLEncoder.encode("upkind","UTF-8") + "=" + URLEncoder.encode("417000", "UTF-8")); /*축종코드 - 개 : 417000 - 고양이 : 422400 - 기타 : 429900 */
//        urlBuilder.append("&" + URLEncoder.encode("kind","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*품종코드 (품종 조회 OPEN API 참조) */
//        urlBuilder.append("&" + URLEncoder.encode("upr_cd","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*시도코드 (시도 조회 OPEN API 참조) */
//        urlBuilder.append("&" + URLEncoder.encode("org_cd","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*시군구코드 (시군구 조회 OPEN API 참조) */
//        urlBuilder.append("&" + URLEncoder.encode("care_reg_no","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*보호소번호 (보호소 조회 OPEN API 참조) */
//        urlBuilder.append("&" + URLEncoder.encode("state","UTF-8") + "=" + URLEncoder.encode("null", "UTF-8")); /*상태 - 전체 : null(빈값) - 공고중 : notice - 보호중 : protect */
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("2", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*페이지당 보여줄 개수*/
//        urlBuilder.append("&" + URLEncoder.encode("neuter_yn","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); /*중성화여부*/
        URL url = new URL(urlBuilder.toString());
        System.out.println(urlBuilder.toString());
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//        conn.setRequestProperty("Content-type", "application/json");
//        System.out.println("Response code: " + conn.getResponseCode());
//        BufferedReader rd;
//        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        } else {
//            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//        }
//        StringBuilder sb = new StringBuilder();
//        String line;
//        while ((line = rd.readLine()) != null) {
//            sb.append(line);
//        }
//        rd.close();
//        conn.disconnect();
//        System.out.println(sb.toString());
        
        DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
		org.w3c.dom.Document doc = dBuilder.parse(urlBuilder.toString()); 

		doc.getDocumentElement().normalize();
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

		NodeList nList = doc.getElementsByTagName("item");
		System.out.println("파싱할 리스트 수 : "+ nList.getLength());
		
		System.out.println("전체");

		for(int temp = 0; temp < nList.getLength(); temp++){
			Node nNode = nList.item(temp);
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
				
				Element eElement = (Element) nNode;
				System.out.println("######################");
				//System.out.println(eElement.getTextContent());
				System.out.println("나이  : " + getTagValue("age", eElement));
				System.out.println("보호장소  : " + getTagValue("careAddr", eElement));
				System.out.println("보호소이름 : " + getTagValue("careNm", eElement));
				System.out.println("보호소번호  : " + getTagValue("careTel", eElement));
				System.out.println("보호자이름  : " + getTagValue("chargeNm", eElement));
				System.out.println("색깔  : " + getTagValue("colorCd", eElement));
				System.out.println("번호  : " + getTagValue("desertionNo", eElement));
				System.out.println("사진  : " + getTagValue("filename", eElement));
				System.out.println("발견 시간  : " + getTagValue("happenDt", eElement));
				System.out.println("발견 장소  : " + getTagValue("happenPlace", eElement));
				System.out.println("종  : " + getTagValue("kindCd", eElement));
			}	// for end
		}
        
    }
}