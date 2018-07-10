package me.khrystal.market.util;

public class PageCalculator {
    public static int calculatePageCount(int totalCount, int pageSize) {
		int idealPage = totalCount / pageSize;
		int totalPage = (totalCount % pageSize == 0) ? idealPage
				: (idealPage + 1);
		return totalPage;
	}

	/**
	 * 从第几条开始选取pageSize个数据
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public static int calculateRowIndex(int pageIndex, int pageSize) {
		return (pageIndex > 0) ? (pageIndex - 1) * pageSize : 0;
	}
}
