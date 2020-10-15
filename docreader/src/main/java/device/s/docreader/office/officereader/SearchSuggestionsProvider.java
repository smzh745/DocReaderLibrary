/*
 * 文件名称:           SearchSuggestionsProvider.java
 *  
 * 编译器:             android2.2
 * 时间:               下午1:44:04
 */
package device.s.docreader.office.officereader;

import android.content.SearchRecentSuggestionsProvider;


public class SearchSuggestionsProvider extends SearchRecentSuggestionsProvider
{
    final static String AUTHORITY = "searchprovider";
    final static int MODE = DATABASE_MODE_QUERIES;
   
    /**
     * 
     */
    public SearchSuggestionsProvider() 
    {
        super();
        setupSuggestions(AUTHORITY, MODE);
    }
}
