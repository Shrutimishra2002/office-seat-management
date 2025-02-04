package filters;

import play.filters.cors.CORSFilter;
import play.http.HttpFilters;
import play.mvc.EssentialFilter;

import javax.inject.Inject;

public class Filters implements HttpFilters {

    private final CORSFilter corsFilter;

    @Inject
    public Filters(CORSFilter corsFilter) {
        this.corsFilter = corsFilter;
    }

    @Override
    public EssentialFilter[] filters() {
        return new EssentialFilter[] { corsFilter.asJava() };
    }
}
