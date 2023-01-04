package ua.com.vp.confapp.utils;




public class Paginator{
    private final Integer NOTES_PER_PAGE;
    private int countOfPages;
    private int page;


    public Paginator(int notesPerPage) {
        NOTES_PER_PAGE = notesPerPage;
    }

//
//
//    public List<Event> getPage(List<Event> items, int page) {
//        int rez = items.size() / NOTES_PER_PAGE;
//        if (items.size() % NOTES_PER_PAGE != 0) {
//            countOfPages = ++rez;
//        } else {
//            countOfPages = rez;
//        }
//        if (page > 0 && page <= countOfPages){
//            this.page = page;
//            return findItems(items, page);
//        } else {
//            this.page = 1;
//            return findItems(items, 1);
//        }
//
//    }
//
//
//    public int getCountOfPages() {
//        return countOfPages;
//    }
//
//    /**
//     * @return page
//     */
//
//    public int getPage() {
//        return page;
//    }
//
//    private List<Event> findItems(List<Event> items, int page){
//        if (items.size() > page * NOTES_PER_PAGE){
//            return items.subList((page - 1) * NOTES_PER_PAGE, page * NOTES_PER_PAGE);
//        } else {
//            return items.subList((page - 1) * NOTES_PER_PAGE, items.size());
//        }
//    }
}
