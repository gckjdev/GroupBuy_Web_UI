package com.orange.groupbuy.web.client.http;

import java.util.ArrayList;
import java.util.List;

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
import com.orange.groupbuy.constant.ServiceConstant;
import com.orange.groupbuy.web.client.model.Criteria;
import com.orange.groupbuy.web.client.model.SearchResult;

public class HttpClient {

	private static String SEARCH_GROUP_BUY_URL_TEMPLATE = "";

	public static interface Callback {
		void updateModel(List<SearchResult> resultList);
	}

	public static void searchGroupBuyHandler(final Criteria criteria,
			final Callback callback) {
		String url = getSearchUrl(criteria);

		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET,
				URL.encode(url));
		try {
			Request request = builder.sendRequest(null, new RequestCallback() {
				@Override
				public void onError(Request request, Throwable exception) {
					// Couldn't connect to server (could be timeout, SOP
					// violation, etc.)
				}

				@Override
				public void onResponseReceived(Request request,
						Response response) {
					if (200 == response.getStatusCode()) {
						// Process the response in response.getText()
					} else {
						String responseText = response.getText();
						if (responseText == null) {
							return;
						}
						try {
							// parse the response text into JSON
							JSONValue jsonValue = JSONParser
									.parseStrict(responseText);
							JSONArray array = jsonValue.isArray();

							if (array == null) {
								throw new JSONException();
							}

							List<SearchResult> searchResultList = jsonToResultList(array);
							callback.updateModel(searchResultList);

						} catch (JSONException e) {
							// TODO:
						}

					}
				}
			});
		} catch (RequestException e) {
			// Couldn't connect to server
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
		return result;
	}

	private static String getSearchUrl(Criteria criteria) {
		String city = criteria.getCity();
		int category = criteria.getCategory().getValue();
		int orderValue = criteria.getOrderType().getValue();
		boolean onlyToday = criteria.isOnlyToday();
		// TODO: parse the data.
		String url = String.format(SEARCH_GROUP_BUY_URL_TEMPLATE,
				criteria.toString());
		return url;
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

	private static List<SearchResult> jsonToResultList(JSONArray array) {
		List<SearchResult> searchResultList = new ArrayList<SearchResult>();
		if (array.size() > 0) {
			JSONObject jsonObj;
			if ((jsonObj = array.get(0).isObject()) != null) {
				JSONValue jsonProduct;
				if ((jsonProduct = jsonObj.get(ServiceConstant.PARA_PRODUCT)) != null) {
					JSONArray productList = jsonProduct.isArray();
					for (int i = 0; i < productList.size(); i++) {
						JSONObject object = array.get(i).isObject();
						if (object != null) {
							SearchResult result = jsonToSearchResult(object);
							searchResultList.add(result);
						}
					}

				}

			}
		}
		return searchResultList;
	}
}
