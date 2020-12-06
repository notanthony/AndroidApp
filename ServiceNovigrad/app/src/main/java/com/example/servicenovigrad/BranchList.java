package com.example.servicenovigrad;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class BranchList extends ArrayAdapter<EmployeeData> {
    private Activity context;
    List<EmployeeData> branches;
    Calendar calendar = Calendar.getInstance();
    int day = calendar.get(Calendar.DAY_OF_WEEK);

    public BranchList(Activity context, List<EmployeeData> branches) {
        super(context, R.layout.layout_service_list, branches);
        this.context = context;
        this.branches = branches;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_branch_list, null, true);

        TextView branchName = (TextView) listViewItem.findViewById(R.id.textViewBranchName);

        TextView phoneNumber = (TextView) listViewItem.findViewById(R.id.textViewBranchPhone);
        TextView address = (TextView) listViewItem.findViewById(R.id.textViewBranchAddress);
        TextView openTimeToday = (TextView) listViewItem.findViewById(R.id.textViewBranchOpenCurrentDay);
        TextView closeTimeToday = (TextView) listViewItem.findViewById(R.id.textViewBranchCloseCurrentDay);

        EmployeeData branch = branches.get(position);
        branchName.setText(branch.getName());
        phoneNumber.setText(branch.getPhoneNumber());
        address.setText(branch.getAddress().toString());
//        ArrayList<String> opening = branch.getOpening();
//        ArrayList<String> closing = branch.getClosing();
//        openTimeToday.setText(opening.get(day));
//        closeTimeToday.setText(closing.get(day));

        return listViewItem;
    }
}

