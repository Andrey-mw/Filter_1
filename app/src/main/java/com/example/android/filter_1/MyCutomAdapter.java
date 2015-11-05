package com.example.android.filter_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android on 05.11.2015.
 */
public class MyCutomAdapter extends ArrayAdapter<Country> {

    private List<Country> countryList;
    private List<Country> originalList;
    private Context context;
    private CountryFilter filter;


    public MyCutomAdapter(Context context, int textViewResourceId, ArrayList<Country> countryList) {
        super(context, textViewResourceId, countryList);
        this.countryList = new ArrayList<Country>();
        this.countryList.addAll(countryList);
        this.originalList = new ArrayList<Country>();
        this.originalList.addAll(countryList);

    }


    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new CountryFilter();
        }
        return filter;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {

            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.country_info, parent, false);

            holder = new ViewHolder();
            holder.code = (TextView) convertView.findViewById(R.id.code);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.continent = (TextView) convertView.findViewById(R.id.continent);
            holder.region = (TextView) convertView.findViewById(R.id.region);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Country country = countryList.get(position);
        holder.code.setText(country.getCode());
        holder.name.setText(country.getName());
        holder.continent.setText(country.getContinent());
        holder.region.setText(country.getRegion());

        return convertView;
    }

    class ViewHolder {
        TextView code;
        TextView name;
        TextView continent;
        TextView region;
    }


    private class CountryFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            constraint = constraint.toString().toLowerCase();
            FilterResults result = new FilterResults();
            if (constraint != null && constraint.toString().length() > 0) {
                ArrayList<Country> filteredItems = new ArrayList<Country>();

                for (int i = 0, l = originalList.size(); i < l; i++) {
                    Country country = originalList.get(i);
                    if (country.toString().toLowerCase().contains(constraint))
                        filteredItems.add(country);
                }
                result.count = filteredItems.size();
                result.values = filteredItems;
            } else {
                synchronized (this) {
                    result.values = originalList;
                    result.count = originalList.size();
                }
            }
            return result;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            countryList = (ArrayList<Country>) results.values;
            notifyDataSetChanged();
            clear();
            for (int i = 0, l = countryList.size(); i < l; i++)
                add(countryList.get(i));
            notifyDataSetInvalidated();

        }
    }
}
