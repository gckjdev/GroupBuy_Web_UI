package com.orange.groupbuy.web.client.component;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class PageListWidget extends Composite {

	private static PageListWidgetUiBinder uiBinder = GWT
			.create(PageListWidgetUiBinder.class);

	interface PageListWidgetUiBinder extends UiBinder<Widget, PageListWidget> {
	}

	private final int pageSize = 20;

	@UiField
	Anchor nextPage;

	@UiField
	Anchor previousPage;

	@UiField
	Label description;

	private int currentPage = 1;
	
	private int totalPages;
	
	private int totalRows;
	
	public PageListWidget() {
		initWidget(uiBinder.createAndBindUi(this));
//		setCurrentPage(1);
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int page) {
        currentPage = page;
        int endRow = getStartRow() + pageSize;
        
        if (currentPage == 1) {
            getPreviousPage().setVisible(false);
            if (totalRows < pageSize) {
                endRow = totalRows;
            }
        } else {
            getPreviousPage().setVisible(true);
        }
        
        if (currentPage >= totalPages) {
            getNextPage().setVisible(false);
            endRow = totalRows;
        } else {
            getNextPage().setVisible(true);
        }
         
        setDescription("第" + String.valueOf(getStartRow() + 1) + " - "
                + String.valueOf(endRow) + "(共" + totalRows +")项");

	}

	public int nextPage() {
		int nextPage = getCurrentPage() + 1;
		setCurrentPage(nextPage);
		return nextPage;
	}

	public int previousPage() {
		int currentPage = getCurrentPage();
		if (currentPage <= 1) {
			currentPage = 1;
			setCurrentPage(currentPage);
			return currentPage;
		}

		int previousPage = getCurrentPage() - 1;
		setCurrentPage(previousPage);
		return previousPage;
	}

	public void setDescription(String text) {
		description.setText(text);
	}

	public int getStartRow() {
		return (currentPage - 1) * pageSize;
	}

	public Anchor getNextPage() {
		return nextPage;
	}

	public Anchor getPreviousPage() {
		return previousPage;
	}

	public int getPageSize() {
		return pageSize;
	}

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPage) {
        this.totalPages = totalPage;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
        this.totalPages = totalRows / pageSize;
        int mod = totalRows % pageSize;
        if (mod > 0) {
            totalPages++;
        }
//        System.out.println("tp:" + totalPages);
        
    }

}
