package top.andyron.wemedia.service;

/**
 * 自媒体文章自动审核
 */
public interface WmNewsAutoScanService {

    /**
     * 自媒体文章审核
     * @param id  自媒体文章id
     */
    public void autoScanWmNews(Integer id);
}
