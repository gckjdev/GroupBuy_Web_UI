package com.orange.groupbuy.web.client.component;

import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Widget;
import com.orange.groupbuy.web.client.event.CityChangedEvent;

public class CityWidget extends DialogBox {

    private static CityWidgetUiBinder uiBinder = GWT.create(CityWidgetUiBinder.class);

    interface CityWidgetUiBinder extends UiBinder<Widget, CityWidget> {
    }
    
    private final EventBus eventBus;

	private String city;

    @UiConstructor
    public CityWidget(EventBus eventBus) {
        super(true, true);
        
        setWidget(uiBinder.createAndBindUi(this));
        this.eventBus = eventBus;
        
        beijing.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent arg0) {
                CityChangedEvent event = new CityChangedEvent();
                event.setCityName(beijing.getText());
                fireEvent(event);
            }
        });
        
        tianjin.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent arg0) {
                CityChangedEvent event = new CityChangedEvent();
                event.setCityName(tianjin.getText());
                fireEvent(event);
            }
        });
        
        shenyang.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent arg0) {
                CityChangedEvent event = new CityChangedEvent();
                event.setCityName(shenyang.getText());
                fireEvent(event);
            }
        });
        
        dalian.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent arg0) {
                CityChangedEvent event = new CityChangedEvent();
                event.setCityName(dalian.getText());
                CityWidget.this.eventBus.fireEvent(event);
            }
        });
        
        changchun.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent arg0) {
                CityChangedEvent event = new CityChangedEvent();
                event.setCityName(changchun.getText());
                fireEvent(event);
            }
        });
        
        haerbin.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent arg0) {
                CityChangedEvent event = new CityChangedEvent();
                event.setCityName(haerbin.getText());
                fireEvent(event);
            }
        });
        
        shijiazhuang.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent arg0) {
                CityChangedEvent event = new CityChangedEvent();
                event.setCityName(shijiazhuang.getText());
                fireEvent(event);
            }
        });
        
        taiyuan.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent arg0) {
                CityChangedEvent event = new CityChangedEvent();
                event.setCityName(taiyuan.getText());
                fireEvent(event);
            }
        });
        
        shanghai.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent arg0) {
                CityChangedEvent event = new CityChangedEvent();
                event.setCityName(shanghai.getText());
                fireEvent(event);
            }
        });
        
        hangzhou.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent arg0) {
                CityChangedEvent event = new CityChangedEvent();
                event.setCityName(hangzhou.getText());
                fireEvent(event);
            }
        });
        
        nanjing.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent arg0) {
                CityChangedEvent event = new CityChangedEvent();
                event.setCityName(nanjing.getText());
                CityWidget.this.eventBus.fireEvent(event);
            }
        });
        
        suzhou.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent arg0) {
                CityChangedEvent event = new CityChangedEvent();
                event.setCityName(suzhou.getText());
                fireEvent(event);
            }
        });
        
        wuxi.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent arg0) {
                CityChangedEvent event = new CityChangedEvent();
                event.setCityName(wuxi.getText());
                fireEvent(event);
            }
        });
        
        changzhou.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent arg0) {
                CityChangedEvent event = new CityChangedEvent();
                event.setCityName(changzhou.getText());
                fireEvent(event);
            }
        });
        
        jinan.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent arg0) {
                CityChangedEvent event = new CityChangedEvent();
                event.setCityName(jinan.getText());
                fireEvent(event);
            }
        });
        
        
        xiamen.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent arg0) {
                CityChangedEvent event = new CityChangedEvent();
                event.setCityName(xiamen.getText());
                fireEvent(event);
            }
        });
        
        ningbo.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent arg0) {
                CityChangedEvent event = new CityChangedEvent();
                event.setCityName(ningbo.getText());
                fireEvent(event);
            }
        });
        
        fuzhou.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent arg0) {
                CityChangedEvent event = new CityChangedEvent();
                event.setCityName(fuzhou.getText());
                fireEvent(event);
            }
        });
        
        qingdao.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent arg0) {
                CityChangedEvent event = new CityChangedEvent();
                event.setCityName(qingdao.getText());
                fireEvent(event);
            }
        });
        
        hefei.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent arg0) {
                CityChangedEvent event = new CityChangedEvent();
                event.setCityName(hefei.getText());
                fireEvent(event);
            }
        });
        
        wuhan.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent arg0) {
                CityChangedEvent event = new CityChangedEvent();
                event.setCityName(wuhan.getText());
                fireEvent(event);
            }
        });
        
        zhengzhou.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent arg0) {
                CityChangedEvent event = new CityChangedEvent();
                event.setCityName(zhengzhou.getText());
                fireEvent(event);
            }
        });
        
        changsha.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent arg0) {
                CityChangedEvent event = new CityChangedEvent();
                event.setCityName(changsha.getText());
                fireEvent(event);
            }
        });
        
        nanchang.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent arg0) {
                CityChangedEvent event = new CityChangedEvent();
                event.setCityName(nanchang.getText());
                fireEvent(event);
            }
        });
        
        
        chengdu.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent arg0) {
                CityChangedEvent event = new CityChangedEvent();
                event.setCityName(chengdu.getText());
                fireEvent(event);
            }
        });
        
        chongqing.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent arg0) {
                CityChangedEvent event = new CityChangedEvent();
                event.setCityName(chongqing.getText());
                fireEvent(event);
            }
        });
        
        xian.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent arg0) {
                CityChangedEvent event = new CityChangedEvent();
                event.setCityName(xian.getText());
                fireEvent(event);
            }
        });
        
        guangzhou.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent arg0) {
                CityChangedEvent event = new CityChangedEvent();
                event.setCityName(guangzhou.getText());
                fireEvent(event);
            }
        });
        
        shenzhen.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent arg0) {
                CityChangedEvent event = new CityChangedEvent();
                event.setCityName(shenzhen.getText());
                fireEvent(event);
            }
        });
        
        dongguan.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent arg0) {
                CityChangedEvent event = new CityChangedEvent();
                event.setCityName(dongguan.getText());
                fireEvent(event);
            }
        });
        
    }

    @UiField
    Anchor beijing;
    
    @UiField
    Anchor tianjin;
    
    @UiField
    Anchor shenyang;
    
    @UiField
    Anchor dalian;
    
    @UiField
    Anchor changchun;
    
    @UiField
    Anchor haerbin;
    
    @UiField
    Anchor shijiazhuang;
    
    @UiField
    Anchor taiyuan;
    
    @UiField
    Anchor shanghai;
    
    @UiField
    Anchor hangzhou;
    
    @UiField
    Anchor nanjing;
    
    @UiField
    Anchor suzhou;
    
    @UiField
    Anchor wuxi;
    
    @UiField
    Anchor changzhou;
    
    @UiField
    Anchor jinan;
    
    @UiField
    Anchor xiamen;
    
    @UiField
    Anchor ningbo;
    
    @UiField
    Anchor fuzhou;
    
    @UiField
    Anchor qingdao;
    
    @UiField
    Anchor hefei;
    
    @UiField
    Anchor wuhan;
    
    @UiField
    Anchor zhengzhou;
    
    @UiField
    Anchor changsha;
    
    @UiField
    Anchor nanchang;
    
    @UiField
    Anchor chengdu;
    
    @UiField
    Anchor chongqing;
    
    @UiField
    Anchor xian;
    
    @UiField
    Anchor guangzhou;
    
    @UiField
    Anchor shenzhen;
    
    @UiField
    Anchor dongguan;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	private void fireEvent(CityChangedEvent event) {
		city = event.getCityName();
		CityWidget.this.eventBus.fireEvent(event);
	}
}
