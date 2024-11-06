package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.core.CrawlType;

public class CrawlSetting {

    private int crawlFrequency;
    private CrawlType crawlType;

    public CrawlSetting(int crawlFrequency, CrawlType crawlType) {
        this.crawlFrequency = crawlFrequency;
        this.crawlType = crawlType;
    }


    public boolean updateIfChanged(CrawlSettingCommand command) {
        boolean isUpdated = false;

        if (this.crawlFrequency != (command.crawlFrequency())) {
            return true;
        }

        if (!this.crawlType.equals(command.crawlType())) {
            return true;
        }

        return isUpdated;
    }



    public int getCrawlFrequency() {
        return crawlFrequency;
    }

    public CrawlType getCrawlType() {
        return crawlType;
    }
}
