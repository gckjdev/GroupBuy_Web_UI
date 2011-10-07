package com.orange.groupbuy.web.client.http;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONException;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.orange.groupbuy.web.client.model.Criteria;
import com.orange.groupbuy.web.client.model.OrderType;
import com.orange.groupbuy.web.client.model.SearchResult;
import com.orange.groupbuy.web.shared.ServiceConstant;

public class HttpClient {
    
    public static final Logger log = Logger.getLogger(HttpClient.class.getName());

	private static String SEARCH_GROUP_BUY_URL_TEMPLATE = "search.proxy?&app=GROUPBUYWEB";

	public static interface Callback {
		void updateModel(List<SearchResult> resultList);
		void updateModel(List<SearchResult> resultList, int rc);
	}

	public static void searchGroupBuyHandler(final Criteria criteria,
			final Callback callback) {
		try {
			String url = getSearchUrl(criteria);
			RequestBuilder builder = new RequestBuilder(RequestBuilder.GET,
					URL.encode(url));
			builder.setCallback(new RequestCallback() {

				@Override
				public void onResponseReceived(Request request,
						Response response) {
					if (response.getStatusCode() == 200) {
						String responseText = response.getText();
						if (responseText == null || responseText.length() == 0) {
							return;
						}
						try {
							// parse the response text into JSON
							JSONValue jsonValue = JSONParser
									.parseStrict(response.getText());

							JSONArray resultList = null;
							JSONObject dataObject = jsonValue.isObject();
							JSONNumber count = null;
							if (dataObject != null) {
//								JSONValue arrayValue = dataObject.get("dat");
//								if (arrayValue != null) {
//									resultList = arrayValue.isArray();
//								}
							    
							    JSONValue arrayValue = dataObject.get("dat");
                                if (arrayValue != null) {
                                    JSONObject listObject = arrayValue.isObject();
                                    if (listObject != null) {
                                        JSONValue listValue = listObject.get("lt");
                                        if (listValue != null) {
                                            resultList = listValue.isArray();
                                        }
                                        count = listObject.get("rc").isNumber();
                                    }
                                }
							    
							}

							List<SearchResult> searchResultList = new ArrayList<SearchResult>();
							int rc = 0;
							if (resultList != null) {
								searchResultList = jsonToResultList(resultList);
							}
							if (count != null) {
							    rc = Integer.valueOf(count.toString()).intValue();
							}
							    
							callback.updateModel(searchResultList, rc);

						} catch (JSONException exception) {
							GWT.log("Exception parseStrict", exception);
							Window.alert("Error: " + exception.getMessage());
						}
					} else {
						Window.alert("Error status code: "
								+ response.getStatusCode());
					}
				}

				@Override
				public void onError(Request request, Throwable exception) {
					Window.alert("Error: " + exception.getMessage());
				}
			});

			builder.send();
		} catch (RequestException e) {
			// TODO: better error handling
			// Couldn't connect to server
			Window.alert("Error: " + e.getMessage());
		} catch (UnsupportedEncodingException e) {
			Window.alert("Error: " + e.getMessage());
		}
	}

	private static SearchResult jsonToSearchResult(JSONObject object) {
		SearchResult result = new SearchResult();
		// set value
		result.setBought((int) getNumber(object, ServiceConstant.PARA_BOUGHT));
		result.setCategory(getString(object, ServiceConstant.PARA_CATEGORY_NAME));
		result.setDesctiption(getString(object, ServiceConstant.PARA_TITLE));
		result.setImageUrl(getString(object, ServiceConstant.PARA_IMAGE));
		result.setPrice(getNumber(object, ServiceConstant.PARA_PRICE));
		result.setProductUrl(getString(object, ServiceConstant.PARA_LOC));
		result.setRebate(getNumber(object, ServiceConstant.PARA_REBATE));
		result.setSiteName(getString(object, ServiceConstant.PARA_SITE_NAME));
		result.setSiteUrl(getString(object, ServiceConstant.PARA_SITE_URL));
		result.setValue(getNumber(object, ServiceConstant.PARA_VALUE));
		result.setStartDate(getString(object, ServiceConstant.PARA_START_DATE));
		result.setEndDate(getString(object, ServiceConstant.PARA_END_DATE));
		return result;
	}

	public static String getSearchUrl(Criteria criteria)
			throws UnsupportedEncodingException {
		int onlyToday = criteria.isOnlyToday() ? 1 : 0;
		int orderValue = OrderType.DATE.getValue();
		if (criteria.getOrderType() != null) {
			orderValue = criteria.getOrderType().getValue();
		}
		String city = criteria.getCity();
		int maxCount = criteria.getPageSize();
		int startRow = criteria.getStartRow();
		String keyword = criteria.getKeyword();

		String url = SEARCH_GROUP_BUY_URL_TEMPLATE;
		StringBuffer sb = new StringBuffer(url);
		sb.append("&m=").append(
				URL.encodeQueryString(criteria.getOperationType()
						.getMethodName()));
		sb.append("&mc=").append(maxCount);
		sb.append("&so=").append(startRow);
		sb.append("&to=").append(onlyToday);
		sb.append("&sb=").append(orderValue);
		sb.append("&ci=").append(city);
		sb.append("&rc=1");
		if (keyword != null) {
			sb.append("&kw=").append(keyword);
		}

		if (criteria.getCategoryList() != null) {
			List<String> category = criteria.getCategoryList();
			for (String c : category) {
				sb.append("&ctg=").append(c);
			}
		}
		if (criteria.getStartPrice() != null) {
			sb.append("&sp=").append(criteria.getStartPrice());
		}
		if (criteria.getEndPrice() != null) {
			sb.append("&ep=").append(criteria.getEndPrice());
		}
		log.info("Post request: " + sb.toString());
		return sb.toString();
	}

	private static double getNumber(JSONObject object, String paraLoc) {
		double returnValue = 0d;
		JSONValue value = object.get(paraLoc);

		if (value != null) {
			JSONNumber concreteValue = value.isNumber();
			if ((concreteValue != null)) {
				returnValue = concreteValue.doubleValue();
			}
		}
		return returnValue;
	}

	private static String getString(JSONObject object, String paraLoc) {
		String returnValue = "";
		JSONValue value = object.get(paraLoc);

		if (value != null) {
			JSONString concreteValue = value.isString();
			if ((concreteValue != null)) {
				returnValue = concreteValue.stringValue();
			}
		}
		return returnValue;
	}

	private static List<SearchResult> jsonToResultList(JSONArray productList) {
		List<SearchResult> searchResultList = new ArrayList<SearchResult>();
		if (productList != null) {
			for (int i = 0; i < productList.size(); i++) {
				JSONObject object = productList.get(i).isObject();
				if (object != null) {
					SearchResult result = jsonToSearchResult(object);
					searchResultList.add(result);
				}
			}
		}
		return searchResultList;
	}
}
