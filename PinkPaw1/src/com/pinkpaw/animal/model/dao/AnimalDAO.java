package com.pinkpaw.animal.model.dao;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.pinkpaw.animal.model.vo.ProtectedAnimal;

public class AnimalDAO {
	
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
	
	public static int AllPA_count = 0;

	public List<ProtectedAnimal> selectAnimalDefault(int cPage) {
		List<ProtectedAnimal> list = new ArrayList<ProtectedAnimal>();
		StringBuilder urlBuilder = null;
		
		SimpleDateFormat format  = new SimpleDateFormat("yyyyMMdd");
		
		Calendar cal = Calendar.getInstance();
		String date1 = format.format(cal.getTime());
		cal.add(Calendar.DATE, -15);
		String date2 = format.format(cal.getTime());
		
		try {
			urlBuilder = new StringBuilder("http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic"); /*URL*/
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=M8qjyG4ZKlORR1wV3ZSNoafubF4A9B8LOwpNKHrOV4CxrLd4HNVJ5JUED7MspOV%2Fw1s2O0Htjnh%2Fr2VtnKfT%2Fw%3D%3D"); /*Service Key*/
			urlBuilder.append("&" + URLEncoder.encode("bgnde","UTF-8") + "=" + URLEncoder.encode(date2, "UTF-8")); /*유기날짜 (검색 시작일) (YYYYMMDD) */
	        urlBuilder.append("&" + URLEncoder.encode("endde","UTF-8") + "=" + URLEncoder.encode(date1, "UTF-8")); /*유기날짜 (검색 종료일) (YYYYMMDD) */
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(cPage+"", "UTF-8")); /*페이지 번호*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("9", "UTF-8")); /*페이지당 보여줄 개수*/
	        
	        DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			org.w3c.dom.Document doc = dBuilder.parse(urlBuilder.toString()); 

			System.out.println(urlBuilder.toString());
			
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("item");
			System.out.println("파싱할 리스트 수 : "+ nList.getLength());

			NodeList count_list = doc.getElementsByTagName("totalCount").item(0).getChildNodes();
		    Node count_node = (Node) count_list.item(0);
		    AllPA_count = Integer.parseInt(count_node.getNodeValue());
			
			for(int temp = 0; temp < nList.getLength(); temp++){
				Node nNode = nList.item(temp);
				if(nNode.getNodeType() == Node.ELEMENT_NODE){
					
					Element eElement = (Element) nNode;
					ProtectedAnimal p = new ProtectedAnimal();
					p.setAge(getTagValue("age", eElement));
					p.setCareAddr(getTagValue("careAddr", eElement));
					p.setCareNm(getTagValue("careNm", eElement));
					p.setCareTel(getTagValue("careTel", eElement));
					p.setChargeNm(getTagValue("chargeNm", eElement));
					p.setColorCd(getTagValue("colorCd", eElement));
					p.setDesertionNo(getTagValue("desertionNo", eElement));
					p.setFilename(getTagValue("filename", eElement));
					p.setHappenDt(getTagValue("happenDt", eElement));
					p.setHappenPlace(getTagValue("happenPlace", eElement));
					p.setKindCd(getTagValue("kindCd", eElement));
					p.setNeuterYn(getTagValue("neuterYn", eElement));
					p.setNoticeEdt(getTagValue("noticeEdt", eElement));
					p.setNoticeNo(getTagValue("noticeNo", eElement));
					p.setNoticeSdt(getTagValue("noticeSdt", eElement));
					try {
						p.setOfficetel(getTagValue("officetel", eElement));
					} catch(Exception e) {
						p.setOfficetel(getTagValue("careTel", eElement));
					}
					p.setOrgNm(getTagValue("orgNm", eElement));
					p.setPopfile(getTagValue("popfile", eElement));
					p.setProcessState(getTagValue("processState", eElement));
					p.setSexCd(getTagValue("sexCd", eElement));
					p.setSpecialMark(getTagValue("specialMark", eElement));
					p.setWeight(getTagValue("weight", eElement));
					list.add(p);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}

	public List<ProtectedAnimal> selectAnimalSrch(int cPage, String anikind, String sido, String sigungu) {
		List<ProtectedAnimal> list = new ArrayList<ProtectedAnimal>();
		StringBuilder urlBuilder = null;
		
		SimpleDateFormat format  = new SimpleDateFormat("yyyyMMdd");
		
		String sido1 = "";
		if("all".equals(sido)) {
			sido1 = "";
		} else {
			sido1 = sido;
		}
		
		Calendar cal = Calendar.getInstance();
		String date1 = format.format(cal.getTime());
		cal.add(Calendar.DATE, -30);
		String date2 = format.format(cal.getTime());
		
		try {
			urlBuilder = new StringBuilder("http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic"); /*URL*/
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=M8qjyG4ZKlORR1wV3ZSNoafubF4A9B8LOwpNKHrOV4CxrLd4HNVJ5JUED7MspOV%2Fw1s2O0Htjnh%2Fr2VtnKfT%2Fw%3D%3D"); /*Service Key*/
			urlBuilder.append("&" + URLEncoder.encode("bgnde","UTF-8") + "=" + URLEncoder.encode(date2, "UTF-8")); /*유기날짜 (검색 시작일) (YYYYMMDD) */
	        urlBuilder.append("&" + URLEncoder.encode("endde","UTF-8") + "=" + URLEncoder.encode(date1, "UTF-8")); /*유기날짜 (검색 종료일) (YYYYMMDD) */
	        urlBuilder.append("&" + URLEncoder.encode("upkind","UTF-8") + "=" + URLEncoder.encode(anikind, "UTF-8")); /*축종코드 - 개 : 417000 - 고양이 : 422400 - 기타 : 429900 */
	        urlBuilder.append("&" + URLEncoder.encode("upr_cd","UTF-8") + "=" + URLEncoder.encode(sido1, "UTF-8")); /*시도코드 (시도 조회 OPEN API 참조) */
	        urlBuilder.append("&" + URLEncoder.encode("org_cd","UTF-8") + "=" + URLEncoder.encode(sigungu, "UTF-8")); /*시군구코드 (시군구 조회 OPEN API 참조) */
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(cPage+"", "UTF-8")); /*페이지 번호*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("9", "UTF-8")); /*페이지당 보여줄 개수*/
	        
	        DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			org.w3c.dom.Document doc = dBuilder.parse(urlBuilder.toString()); 

			System.out.println(urlBuilder.toString());
			
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("item");
			System.out.println("파싱할 리스트 수 : "+ nList.getLength());

			NodeList count_list = doc.getElementsByTagName("totalCount").item(0).getChildNodes();
		    Node count_node = (Node) count_list.item(0);
		    AllPA_count = Integer.parseInt(count_node.getNodeValue());
			
			for(int temp = 0; temp < nList.getLength(); temp++){
				Node nNode = nList.item(temp);
				if(nNode.getNodeType() == Node.ELEMENT_NODE){
					
					Element eElement = (Element) nNode;
					ProtectedAnimal p = new ProtectedAnimal();
					p.setAge(getTagValue("age", eElement));
					p.setCareAddr(getTagValue("careAddr", eElement));
					p.setCareNm(getTagValue("careNm", eElement));
					p.setCareTel(getTagValue("careTel", eElement));
					p.setChargeNm(getTagValue("chargeNm", eElement));
					p.setColorCd(getTagValue("colorCd", eElement));
					p.setDesertionNo(getTagValue("desertionNo", eElement));
					p.setFilename(getTagValue("filename", eElement));
					p.setHappenDt(getTagValue("happenDt", eElement));
					p.setHappenPlace(getTagValue("happenPlace", eElement));
					p.setKindCd(getTagValue("kindCd", eElement));
					p.setNeuterYn(getTagValue("neuterYn", eElement));
					p.setNoticeEdt(getTagValue("noticeEdt", eElement));
					p.setNoticeNo(getTagValue("noticeNo", eElement));
					p.setNoticeSdt(getTagValue("noticeSdt", eElement));
					try {
						p.setOfficetel(getTagValue("officetel", eElement));
					} catch(Exception e) {
						p.setOfficetel(getTagValue("careTel", eElement));
					}
					p.setOrgNm(getTagValue("orgNm", eElement));
					p.setPopfile(getTagValue("popfile", eElement));
					p.setProcessState(getTagValue("processState", eElement));
					p.setSexCd(getTagValue("sexCd", eElement));
					p.setSpecialMark(getTagValue("specialMark", eElement));
					p.setWeight(getTagValue("weight", eElement));
					list.add(p);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}

}
