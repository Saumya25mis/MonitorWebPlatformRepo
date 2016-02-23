/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.monitor.utilx;

import com.google.gson.Gson;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author aubreyM
 */
public class TestJSON {
    
    public static void main(String[] args) {
        Object obj = gson.fromJson(json, Object.class);
        if (obj == null) {
            
        }
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(Calendar.YEAR, 1990);
        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 26);
        
        Date date = cal.getTime();
        System.out.println("The date is: " + date.toString());
                
    }
    static final Gson gson = new Gson();
    static final String json = "{\n" +
"  \"LoginResult\":{\n" +
"    \"UserProfile\":{\n" +
"      \"CustomerID\":\"31939\",\n" +
"      \"CustomerType\":\"Person\",\n" +
"      \"IDDocument\":{\n" +
"        \"IDNumber\":\"3301045068086\",\n" +
"        \"IDType\":\"South African ID\",\n" +
"        \"CountryOfIssue\":null\n" +
"      },\n" +
"      \"Title\":\"Mr\",\n" +
"      \"FirstName\":\"WALTER TRAYNOR\",\n" +
"      \"LastName\":\"RODD\",\n" +
"      \"CellNumber\":\"0829234625\",\n" +
"      \"HomeNumber\":null,\n" +
"      \"WorkNumber\":null,\n" +
"      \"ManAgent\":\"false\",\n" +
"      \"OrganisationName\":null,\n" +
"      \"ContactPerson\":null,\n" +
"      \"ContactPosition\":null,\n" +
"      \"GovernmentDepID\":\"0\",\n" +
"      \"GovernmentBranch\":null,\n" +
"      \"Email1\":\"walmay@eject.co.za\",\n" +
"      \"Email1Status\":\"activated\",\n" +
"      \"Email2\":null,\n" +
"      \"DateActivated\":\"2010-05-16T00:00:00+02:00\",\n" +
"      \"EmployeeName\":null,\n" +
"      \"CustomertStatus\":\"registered\",\n" +
"      \"CapUserID\":null,\n" +
"      \"CapDate\":\"2014-11-18T09:21:05.633+02:00\"\n" +
"    },\n" +
"    \"ns0:Accounts\":[\n" +
"      {\n" +
"        \"xmlns:ns0\":\"http//dynatech.co.za/ism/mobile/useraccounts/useraccounts.xsd\",\n" +
"        \"ns0:AccountNumber\":\"42105547610\",\n" +
"        \"ns0:PropertyAddress\":\"4 SUTTON AVE,UMHLANGA ROCKS,UMHLANGA ROCKS\",\n" +
"        \"ns0:AccountDetails\":{\n" +
"          \"ns0:DateLastUpdated\":\"2015-03-29T10:09:18.387+02:00\",\n" +
"          \"ns0:CurrentBalance\":\"2811.84\",\n" +
"          \"ns0:TotalAccAmount\":\"2811.84\",\n" +
"          \"ns0:CashAfterAcct\":\"0\",\n" +
"          \"ns0:CustomerNumber\":\"94294\",\n" +
"          \"ns0:CustomerAccName\":\"WT RODD\",\n" +
"          \"ns0:RMSPropertyAddress\":\"4,SUTTON AVENUE,UMHLANGA ROCKS\",\n" +
"          \"ns0:LastBillAmount\":\"2811.84\",\n" +
"          \"ns0:NextBillDate\":\"27 Apr 2015\",\n" +
"          \"ns0:CurrentArrears\":\"0\",\n" +
"          \"ns0:BillDay\":\"20\",\n" +
"          \"ns0:PreviousBillDate\":\"27 Mar 2015\",\n" +
"          \"ns0:CustomerIdentityNo\":\"3301045068086\"\n" +
"        }\n" +
"      }\n" +
"    ],\n" +
"    \"newsSummary\":[\n" +
"      {\n" +
"        \"updated\":\"2015-03-19T11:09:46.651+02:00\",\n" +
"        \"id\":\"681\",\n" +
"        \"description\":\"Water alert\",\n" +
"        \"title\":\"Water alert\",\n" +
"        \"category\":\"Alerts\",\n" +
"        \"latitude\":\"-29.85851045231202\",\n" +
"        \"longitude\":\"31.010971069335938\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=https://pbs.twimg.com/profile_images/1571059372/Logo-Thekwini__2_.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/681\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-19T11:08:26.588+02:00\",\n" +
"        \"id\":\"680\",\n" +
"        \"description\":\"In memory of the late Minister of Public Service\",\n" +
"        \"title\":\"In memory of the late Minister of Public Service\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":\"-29.85851045231202\",\n" +
"        \"longitude\":\"31.02264404296875\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/680\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-19T09:43:11.853+02:00\",\n" +
"        \"id\":\"679\",\n" +
"        \"description\":\"Public Notice \",\n" +
"        \"title\":\"Public Notice \",\n" +
"        \"category\":\"Alerts\",\n" +
"        \"latitude\":\"-29.859701442126745\",\n" +
"        \"longitude\":\"31.02264404296875\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=https://pbs.twimg.com/profile_images/1571059372/Logo-Thekwini__2_.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/679\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-19T09:41:08.359+02:00\",\n" +
"        \"id\":\"678\",\n" +
"        \"description\":\"Roadworks \",\n" +
"        \"title\":\"Roadworks \",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":null,\n" +
"        \"longitude\":null,\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=https://pbs.twimg.com/profile_images/1571059372/Logo-Thekwini__2_.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/678\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-19T09:21:59.072+02:00\",\n" +
"        \"id\":\"676\",\n" +
"        \"description\":\"Congestion\",\n" +
"        \"title\":\"Congestion \",\n" +
"        \"category\":\"Alerts\",\n" +
"        \"latitude\":\"-29.720277915631627\",\n" +
"        \"longitude\":\"31.060194969177246\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=https://pbs.twimg.com/profile_images/1571059372/Logo-Thekwini__2_.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/676\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-19T09:17:12.015+02:00\",\n" +
"        \"id\":\"675\",\n" +
"        \"description\":\"Water alert \",\n" +
"        \"title\":\"Water alert \",\n" +
"        \"category\":\"Alerts\",\n" +
"        \"latitude\":\"-29.86089241772907\",\n" +
"        \"longitude\":\"31.013031005859375\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=https://pbs.twimg.com/profile_images/1571059372/Logo-Thekwini__2_.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/675\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-19T09:14:55.217+02:00\",\n" +
"        \"id\":\"674\",\n" +
"        \"description\":\"3rd International Conference on Innovation and Entrepreneurship\",\n" +
"        \"title\":\"3rd International Conference on Innovation and Entrepreneurship\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":\"-29.857319448285196\",\n" +
"        \"longitude\":\"31.00616455078125\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/674\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-19T09:11:45.791+02:00\",\n" +
"        \"id\":\"673\",\n" +
"        \"description\":\"New build programme creates thousands of jobs\",\n" +
"        \"title\":\"New build programme creates thousands of jobs\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":\"-29.854937397596693\",\n" +
"        \"longitude\":\"31.0089111328125\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/673\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-18T14:14:57.348+02:00\",\n" +
"        \"id\":\"672\",\n" +
"        \"description\":\"Water Alert \",\n" +
"        \"title\":\"Water Alert \",\n" +
"        \"category\":\"Alerts\",\n" +
"        \"latitude\":\"-29.85851045231202\",\n" +
"        \"longitude\":\"31.013031005859375\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=https://pbs.twimg.com/profile_images/1571059372/Logo-Thekwini__2_.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/672\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-18T14:09:15.774+02:00\",\n" +
"        \"id\":\"671\",\n" +
"        \"description\":\"Current Outages \",\n" +
"        \"title\":\"Current Outages \",\n" +
"        \"category\":\"Alerts\",\n" +
"        \"latitude\":\"-29.859105948995914\",\n" +
"        \"longitude\":\"31.019554138183594\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=https://pbs.twimg.com/profile_images/1571059372/Logo-Thekwini__2_.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/671\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-18T14:00:44.066+02:00\",\n" +
"        \"id\":\"670\",\n" +
"        \"description\":\"Municipal services continuing as usual\",\n" +
"        \"title\":\"Municipal services continuing as usual\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":null,\n" +
"        \"longitude\":null,\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/670\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-17T19:42:07.718+02:00\",\n" +
"        \"id\":\"662\",\n" +
"        \"description\":\"Conference:19-20\",\n" +
"        \"title\":\"Conference:19-20\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":\"-29.869228848968298\",\n" +
"        \"longitude\":\"30.98968505859375\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/662\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-17T19:39:53.949+02:00\",\n" +
"        \"id\":\"661\",\n" +
"        \"description\":\"Electricity alert\",\n" +
"        \"title\":\"Electricity alert\",\n" +
"        \"category\":\"Alerts\",\n" +
"        \"latitude\":\"-29.816965794305517\",\n" +
"        \"longitude\":\"30.848922729492188\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=https://pbs.twimg.com/profile_images/1571059372/Logo-Thekwini__2_.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/661\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-17T19:35:18.09+02:00\",\n" +
"        \"id\":\"660\",\n" +
"        \"description\":\"Minister Chabane's funeral set for Saturday\",\n" +
"        \"title\":\"Minister Chabane's funeral set for Saturday\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":null,\n" +
"        \"longitude\":null,\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/660\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-17T19:33:35.55+02:00\",\n" +
"        \"id\":\"659\",\n" +
"        \"description\":\"Conference: 19-20 March\",\n" +
"        \"title\":\"Conference: 19-20 March\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":\"-29.86327432629502\",\n" +
"        \"longitude\":\"30.98968505859375\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/659\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-17T19:29:05.924+02:00\",\n" +
"        \"id\":\"657\",\n" +
"        \"description\":\"Water alert\",\n" +
"        \"title\":\"Water alert\",\n" +
"        \"category\":\"Alerts\",\n" +
"        \"latitude\":\"-29.860296931704475\",\n" +
"        \"longitude\":\"30.993804931640625\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=https://pbs.twimg.com/profile_images/1571059372/Logo-Thekwini__2_.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/657\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-17T19:21:40.396+02:00\",\n" +
"        \"id\":\"655\",\n" +
"        \"description\":\"Water alert\",\n" +
"        \"title\":\"Water alert\",\n" +
"        \"category\":\"Alerts\",\n" +
"        \"latitude\":\"-29.862083379118598\",\n" +
"        \"longitude\":\"31.004791259765625\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=https://pbs.twimg.com/profile_images/1571059372/Logo-Thekwini__2_.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/655\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-17T19:18:05.979+02:00\",\n" +
"        \"id\":\"654\",\n" +
"        \"description\":\"Water alert\",\n" +
"        \"title\":\"Water alert\",\n" +
"        \"category\":\"Alerts\",\n" +
"        \"latitude\":\"-29.859105948995914\",\n" +
"        \"longitude\":\"31.017494201660156\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=https://pbs.twimg.com/profile_images/1571059372/Logo-Thekwini__2_.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/654\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-17T12:23:41.127+02:00\",\n" +
"        \"id\":\"651\",\n" +
"        \"description\":\"Water alert\",\n" +
"        \"title\":\"Water alert\",\n" +
"        \"category\":\"Alerts\",\n" +
"        \"latitude\":\"-29.86089241772907\",\n" +
"        \"longitude\":\"31.010284423828125\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=https://pbs.twimg.com/profile_images/1571059372/Logo-Thekwini__2_.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/651\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-17T12:19:01.55+02:00\",\n" +
"        \"id\":\"649\",\n" +
"        \"description\":\"Event: Members of the media are invited\",\n" +
"        \"title\":\"Event: Members of the media are invited\",\n" +
"        \"category\":\"Alerts\",\n" +
"        \"latitude\":\"-29.812050767525037\",\n" +
"        \"longitude\":\"31.0528564453125\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=https://pbs.twimg.com/profile_images/1571059372/Logo-Thekwini__2_.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/649\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-12T10:15:06.462+02:00\",\n" +
"        \"id\":\"625\",\n" +
"        \"description\":\"Durban has just been awarded another feather\",\n" +
"        \"title\":\"Durban has just been awarded another feather\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":\"-29.864465259257987\",\n" +
"        \"longitude\":\"31.025390625\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/625\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-11T12:01:23.035+02:00\",\n" +
"        \"id\":\"619\",\n" +
"        \"description\":\"KZN region Drought Campaign and National Water Week\",\n" +
"        \"title\":\"KZN region Drought Campaign and National Water Week\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":\"-29.86148790020046\",\n" +
"        \"longitude\":\"31.015090942382812\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/619\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-11T11:51:23.8+02:00\",\n" +
"        \"id\":\"618\",\n" +
"        \"description\":\"South Regional Business Fair\",\n" +
"        \"title\":\"South Regional Business Fair\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":\"-29.86327432629502\",\n" +
"        \"longitude\":\"31.0198974609375\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/618\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-11T09:34:34.244+02:00\",\n" +
"        \"id\":\"615\",\n" +
"        \"description\":\"Business Fair Bookings\",\n" +
"        \"title\":\"Business Fair Bookings\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":\"-29.864465259257987\",\n" +
"        \"longitude\":\"31.01715087890625\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/615\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-09T09:49:06.047+02:00\",\n" +
"        \"id\":\"608\",\n" +
"        \"description\":\"New 7 Wonders City \",\n" +
"        \"title\":\"New 7 Wonders City \",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":\"-29.86089241772907\",\n" +
"        \"longitude\":\"31.0198974609375\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/608\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-09T09:43:05.521+02:00\",\n" +
"        \"id\":\"606\",\n" +
"        \"description\":\"Calling all youth to enter\",\n" +
"        \"title\":\"Calling all youth to enter\",\n" +
"        \"category\":\"Alerts\",\n" +
"        \"latitude\":null,\n" +
"        \"longitude\":null,\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=https://pbs.twimg.com/profile_images/1571059372/Logo-Thekwini__2_.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/606\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-09T09:40:02.752+02:00\",\n" +
"        \"id\":\"605\",\n" +
"        \"description\":\"Current outages\",\n" +
"        \"title\":\"Current outages\",\n" +
"        \"category\":\"Alerts\",\n" +
"        \"latitude\":\"-29.86327432629502\",\n" +
"        \"longitude\":\"31.007537841796875\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=https://pbs.twimg.com/profile_images/1571059372/Logo-Thekwini__2_.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/605\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-09T09:22:09.79+02:00\",\n" +
"        \"id\":\"603\",\n" +
"        \"description\":\"Calling all youth to enter the exciting Youth Innovation Challenge\",\n" +
"        \"title\":\"Calling all youth to enter the exciting Youth Innovation Challenge\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":\"-29.866847082542137\",\n" +
"        \"longitude\":\"30.9814453125\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/603\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-09T09:20:36.528+02:00\",\n" +
"        \"id\":\"602\",\n" +
"        \"description\":\"Will your community join in?\",\n" +
"        \"title\":\"Will your community join in?\",\n" +
"        \"category\":\"Alerts\",\n" +
"        \"latitude\":\"-29.859701442126745\",\n" +
"        \"longitude\":\"30.9869384765625\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=https://pbs.twimg.com/profile_images/1571059372/Logo-Thekwini__2_.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/602\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-09T09:19:04.939+02:00\",\n" +
"        \"id\":\"601\",\n" +
"        \"description\":\"Durban named SA city with highest quality of life\",\n" +
"        \"title\":\"Durban named SA city with highest quality of life\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":\"-29.857319448285196\",\n" +
"        \"longitude\":\"31.0198974609375\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/601\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-05T14:35:23.894+02:00\",\n" +
"        \"id\":\"598\",\n" +
"        \"description\":\"Opportunity for SA companies to market themselves\",\n" +
"        \"title\":\"Opportunity for SA companies to market themselves\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":null,\n" +
"        \"longitude\":null,\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/598\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-05T13:58:15.222+02:00\",\n" +
"        \"id\":\"596\",\n" +
"        \"description\":\"Interested youth willing to join the Hackathon\",\n" +
"        \"title\":\"Interested youth willing to join the Hackathon\",\n" +
"        \"category\":\"Alerts\",\n" +
"        \"latitude\":\"-29.868037972862645\",\n" +
"        \"longitude\":\"31.007537841796875\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=https://pbs.twimg.com/profile_images/1571059372/Logo-Thekwini__2_.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/596\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-11T09:00:31.678+02:00\",\n" +
"        \"id\":\"595\",\n" +
"        \"description\":\"Roadworks, Drive Carefully \",\n" +
"        \"title\":\"Roadworks, Drive Carefully\",\n" +
"        \"category\":\"Alerts\",\n" +
"        \"latitude\":\"-29.739414283440517\",\n" +
"        \"longitude\":\"30.655406713485718\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=https://pbs.twimg.com/profile_images/1571059372/Logo-Thekwini__2_.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/595\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-05T09:49:10.95+02:00\",\n" +
"        \"id\":\"594\",\n" +
"        \"description\":\"Current Outages \",\n" +
"        \"title\":\"Current Outages \",\n" +
"        \"category\":\"Alerts\",\n" +
"        \"latitude\":\"-29.862083379118598\",\n" +
"        \"longitude\":\"31.01715087890625\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=https://pbs.twimg.com/profile_images/1571059372/Logo-Thekwini__2_.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/594\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-05T09:41:04.127+02:00\",\n" +
"        \"id\":\"593\",\n" +
"        \"description\":\"Innovate Durban\",\n" +
"        \"title\":\"Innovate Durban\",\n" +
"        \"category\":\"Alerts\",\n" +
"        \"latitude\":\"-29.862083379118598\",\n" +
"        \"longitude\":\"31.02264404296875\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=https://pbs.twimg.com/profile_images/1571059372/Logo-Thekwini__2_.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/593\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-05T09:38:00.958+02:00\",\n" +
"        \"id\":\"592\",\n" +
"        \"description\":\"Registration for the 'Hackathon' \",\n" +
"        \"title\":\"Registration for the 'Hackathon' \",\n" +
"        \"category\":\"Alerts\",\n" +
"        \"latitude\":\"-29.859701442126745\",\n" +
"        \"longitude\":\"31.0198974609375\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=https://pbs.twimg.com/profile_images/1571059372/Logo-Thekwini__2_.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/592\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-05T09:36:22.717+02:00\",\n" +
"        \"id\":\"591\",\n" +
"        \"description\":\"Urgent Notice: Planned Water Shut Down That Will Affect Parts Of Pinetown Today; 05 March 2015\",\n" +
"        \"title\":\"Urgent Notice: Planned Water Shut Down That Will Affect Parts Of Pinetown Today; 05 March 2015\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":\"-29.86089241772907\",\n" +
"        \"longitude\":\"31.007537841796875\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/591\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-05T09:34:48.379+02:00\",\n" +
"        \"id\":\"590\",\n" +
"        \"description\":\"Urgent Notice: Planned Water Shut Down That Will Affect Parts Of Pinetown Today; 05 March 2015\",\n" +
"        \"title\":\"Urgent Notice: Planned Water Shut Down That Will Affect Parts Of Pinetown Today; 05 March 2015\",\n" +
"        \"category\":\"Alerts\",\n" +
"        \"latitude\":\"-29.852555290064007\",\n" +
"        \"longitude\":\"31.01165771484375\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=https://pbs.twimg.com/profile_images/1571059372/Logo-Thekwini__2_.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/590\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-04T14:09:16.859+02:00\",\n" +
"        \"id\":\"589\",\n" +
"        \"description\":\"Pensioner rebate renewal forms: Due by 30th of April\",\n" +
"        \"title\":\"Pensioner rebate renewal forms: Due by 30th of April \",\n" +
"        \"category\":\"Alerts\",\n" +
"        \"latitude\":\"-29.86089241772907\",\n" +
"        \"longitude\":\"31.010284423828125\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=https://pbs.twimg.com/profile_images/1571059372/Logo-Thekwini__2_.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/589\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-04T14:00:16+02:00\",\n" +
"        \"id\":\"588\",\n" +
"        \"description\":\"Innovate Durban Challenge \",\n" +
"        \"title\":\"Innovate Durban Challenge \",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":\"-29.860594675160915\",\n" +
"        \"longitude\":\"31.015777587890625\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=https://pbs.twimg.com/profile_images/1571059372/Logo-Thekwini__2_.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/588\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-04T13:58:22.785+02:00\",\n" +
"        \"id\":\"587\",\n" +
"        \"description\":\"Innovate Durban: Smarter eThekwini\",\n" +
"        \"title\":\"Innovate Durban: Smarter eThekwini\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":null,\n" +
"        \"longitude\":null,\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/587\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-04T13:40:07.75+02:00\",\n" +
"        \"id\":\"586\",\n" +
"        \"description\":\"Innovate Durban Registration\",\n" +
"        \"title\":\"Innovate Durban Registration \",\n" +
"        \"category\":\"Alerts\",\n" +
"        \"latitude\":\"-29.85851045231202\",\n" +
"        \"longitude\":\"31.0089111328125\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=https://pbs.twimg.com/profile_images/1571059372/Logo-Thekwini__2_.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/586\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-04T13:56:05.668+02:00\",\n" +
"        \"id\":\"585\",\n" +
"        \"description\":\"Innovate Durban \",\n" +
"        \"title\":\"Innovate Durban\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":\"-29.859701442126745\",\n" +
"        \"longitude\":\"31.00067138671875\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/585\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-04T13:34:22.157+02:00\",\n" +
"        \"id\":\"584\",\n" +
"        \"description\":\"Budget places economy on solid foundation for growth\",\n" +
"        \"title\":\"Budget places economy on solid foundation for growth\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":\"-29.859701442126745\",\n" +
"        \"longitude\":\"31.017837524414062\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/584\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-04T13:32:37.979+02:00\",\n" +
"        \"id\":\"583\",\n" +
"        \"description\":\"Government to deal with fraudulent qualifications\",\n" +
"        \"title\":\"Government to deal with fraudulent qualifications\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":\"-29.86089241772907\",\n" +
"        \"longitude\":\"31.02264404296875\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/583\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-04T13:35:06.01+02:00\",\n" +
"        \"id\":\"582\",\n" +
"        \"description\":\"Innovate Durban \",\n" +
"        \"title\":\"Innovate Durban \",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":\"-29.857319448285196\",\n" +
"        \"longitude\":\"31.00616455078125\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/582\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-04T10:06:45.231+02:00\",\n" +
"        \"id\":\"581\",\n" +
"        \"description\":\"Youth Innovation Challenge\",\n" +
"        \"title\":\"Youth Innovation Challenge\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":\"-29.86327432629502\",\n" +
"        \"longitude\":\"31.0089111328125\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/581\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-04T09:59:30.97+02:00\",\n" +
"        \"id\":\"580\",\n" +
"        \"description\":\"We are so excited to introduce our Youth Innovation Challenge\",\n" +
"        \"title\":\"We are so excited to introduce our Youth Innovation Challenge\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":\"-29.862083379118598\",\n" +
"        \"longitude\":\"31.01165771484375\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/580\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-04T08:58:05.298+02:00\",\n" +
"        \"id\":\"577\",\n" +
"        \"description\":\"Are you interested in getting your hands on the latest IBM technology?\",\n" +
"        \"title\":\"Are you interested in getting your hands on the latest IBM technology?\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":\"-29.859999187359755\",\n" +
"        \"longitude\":\"31.01886749267578\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/577\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-03T12:37:02.428+02:00\",\n" +
"        \"id\":\"575\",\n" +
"        \"description\":\"Durban 2022\",\n" +
"        \"title\":\"Durban 2022\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":\"-29.863869794553214\",\n" +
"        \"longitude\":\"31.021270751953125\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/575\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-03T12:22:28.257+02:00\",\n" +
"        \"id\":\"572\",\n" +
"        \"description\":\"Crash\",\n" +
"        \"title\":\"Crash\",\n" +
"        \"category\":\"Alerts\",\n" +
"        \"latitude\":\"-29.840346096874626\",\n" +
"        \"longitude\":\"30.97774386405945\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=https://pbs.twimg.com/profile_images/1571059372/Logo-Thekwini__2_.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/572\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-03T12:18:48.626+02:00\",\n" +
"        \"id\":\"571\",\n" +
"        \"description\":\"So So So Excited for this! \",\n" +
"        \"title\":\"So So So Excited for this! \",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":null,\n" +
"        \"longitude\":null,\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/571\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-02T15:50:47.495+02:00\",\n" +
"        \"id\":\"570\",\n" +
"        \"description\":\"Nominate Your 2015 Living Legend\",\n" +
"        \"title\":\"Nominate Your 2015 Living Legend\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":null,\n" +
"        \"longitude\":null,\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/570\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-02T09:06:42.601+02:00\",\n" +
"        \"id\":\"567\",\n" +
"        \"description\":\"Western Aqueduct under way\",\n" +
"        \"title\":\"Western Aqueduct under way\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":\"-29.85791495207511\",\n" +
"        \"longitude\":\"31.00994110107422\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/567\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-03-02T09:02:14.769+02:00\",\n" +
"        \"id\":\"565\",\n" +
"        \"description\":\"Medium to high risk of power cuts\",\n" +
"        \"title\":\"Medium to high risk of power cuts\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":null,\n" +
"        \"longitude\":null,\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/565\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-02-26T15:06:32.705+02:00\",\n" +
"        \"id\":\"563\",\n" +
"        \"description\":\"Increase on personal income tax, fuel levies proposed\",\n" +
"        \"title\":\"Increase on personal income tax, fuel levies proposed\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":\"-29.862083379118598\",\n" +
"        \"longitude\":\"31.010284423828125\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/563\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-02-26T15:04:44.582+02:00\",\n" +
"        \"id\":\"562\",\n" +
"        \"description\":\"Ministers welcome pro-poor budget 2015\",\n" +
"        \"title\":\"Ministers welcome pro-poor budget 2015\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":\"-29.857319448285196\",\n" +
"        \"longitude\":\"30.98419189453125\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/562\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-02-25T11:15:54.579+02:00\",\n" +
"        \"id\":\"558\",\n" +
"        \"description\":\"PUBLIC NOTICE\",\n" +
"        \"title\":\"PUBLIC NOTICE\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":\"-29.85940369600547\",\n" +
"        \"longitude\":\"31.015777587890625\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=https://pbs.twimg.com/profile_images/1571059372/Logo-Thekwini__2_.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/558\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-02-25T11:04:34.947+02:00\",\n" +
"        \"id\":\"557\",\n" +
"        \"description\":\"Youth lap up chance to shape their future\",\n" +
"        \"title\":\"Youth lap up chance to shape their future\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":\"-29.878755346037963\",\n" +
"        \"longitude\":\"30.9979248046875\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/557\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-02-23T10:19:21.258+02:00\",\n" +
"        \"id\":\"543\",\n" +
"        \"description\":\"The news is out! Durban\\u2019s time is now\",\n" +
"        \"title\":\"The news is out! Durban\\u2019s time is now\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":\"-29.857319448285196\",\n" +
"        \"longitude\":\"31.006851196289062\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/543\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-02-19T04:53:48.478+02:00\",\n" +
"        \"id\":\"527\",\n" +
"        \"description\":\"Public Notice: Draft Rates Policy\",\n" +
"        \"title\":\"Public Notice: Draft Rates Policy\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":\"-29.82158272057499\",\n" +
"        \"longitude\":\"31.0089111328125\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/527\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-02-13T10:47:14.215+02:00\",\n" +
"        \"id\":\"512\",\n" +
"        \"description\":\"President unveils bold plan to tackle energy crisis\",\n" +
"        \"title\":\"President unveils bold plan to tackle energy crisis\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":\"-29.858882638155823\",\n" +
"        \"longitude\":\"31.020240783691406\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/512\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-02-11T08:45:36.678+02:00\",\n" +
"        \"id\":\"495\",\n" +
"        \"description\":\"'Eskom' statement a hoax\",\n" +
"        \"title\":\"'Eskom' statement a hoax\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":\"-29.858957075158045\",\n" +
"        \"longitude\":\"31.021957397460938\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/495\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-02-10T15:16:13.331+02:00\",\n" +
"        \"id\":\"491\",\n" +
"        \"description\":\"EThekwini and US talk trade\",\n" +
"        \"title\":\"EThekwini and US talk trade\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":\"-29.859999187359755\",\n" +
"        \"longitude\":\"31.021442413330078\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/491\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-02-10T09:33:16.307+02:00\",\n" +
"        \"id\":\"463\",\n" +
"        \"description\":\"Load shedding advisory\",\n" +
"        \"title\":\"Load shedding advisory\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":\"-29.85851045231202\",\n" +
"        \"longitude\":\"31.01715087890625\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/463\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2015-01-29T15:03:02.442+02:00\",\n" +
"        \"id\":\"441\",\n" +
"        \"description\":\"Low risk of power cuts - Eskom\",\n" +
"        \"title\":\"Low risk of power cuts - Eskom\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":\"-29.85877098254841\",\n" +
"        \"longitude\":\"31.02062702178955\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/441\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2014-11-26T09:15:23.402+02:00\",\n" +
"        \"id\":\"239\",\n" +
"        \"description\":\"End the silence, speak out against violence\",\n" +
"        \"title\":\"End the silence, speak out against violence\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":\"-29.858780287187138\",\n" +
"        \"longitude\":\"31.021453142166138\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/239\"\n" +
"      },\n" +
"      {\n" +
"        \"updated\":\"2014-11-13T11:38:08.836+02:00\",\n" +
"        \"id\":\"183\",\n" +
"        \"description\":\"Load Shedding - Eskom coal silo collapses at Majuba\",\n" +
"        \"title\":\"Load Shedding - Eskom coal silo collapses at Majuba\",\n" +
"        \"category\":\"News\",\n" +
"        \"latitude\":\"-29.858547670958853\",\n" +
"        \"longitude\":\"31.02266550064087\",\n" +
"        \"thumbnailURL\":\"https://mobile.durban.gov.za/esbapi/V1/accounts_pdf/0000/pdfStatement/2014/1?Token=http://oi59.tinypic.com/f4o3ft.jpg\",\n" +
"        \"href\":\"https://mobile.durban.gov.za/esbapi/V1/news/183\"\n" +
"      }\n" +
"    ]\n" +
"  }\n" +
"}";
}
