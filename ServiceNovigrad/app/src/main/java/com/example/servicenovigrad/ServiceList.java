package com.example.servicenovigrad;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ServiceList extends ArrayAdapter<Service> {
    private Activity context;
    List<Service> services;

    public ServiceList(Activity context, List<Service> services) {
        super(context, R.layout.layout_service_list, services);
        this.context = context;
        this.services = services;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_service_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewId = (TextView) listViewItem.findViewById(R.id.textViewId);
        TextView textViewPrice = (TextView) listViewItem.findViewById(R.id.textViewPrice);
        TextView textViewDocuments = (TextView) listViewItem.findViewById(R.id.textViewDocuments);
        TextView textViewForms = (TextView) listViewItem.findViewById(R.id.textViewForms);


        Service service = services.get(position);
        textViewName.setText("Name: " + service.getServiceName());
        textViewId.setText("ID: " + service.getId());
        textViewPrice.setText("Price " + String.valueOf(service.getPrice()));

//        String documents = "Documents: ";
        List<String> docList = service.getDocs();
        List<String> formList = service.getForms();

//        textViewForms.setText(Arrays.toString(formList.toArray()));
//        textViewDocuments.setText(Arrays.toString(docList.toArray()));

//
//        String forms = "";
//        for (int i=0; i< service.getForms().length;i++) {
//            forms += service.getForms()[i];
//        }
//        textViewForms.setText(service.getForms().toString());

        return listViewItem;
    }
}

