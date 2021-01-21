package com.del.androidclasses.adapters;

import android.widget.BaseAdapter;
import android.widget.Filterable;
import android.content.Context;
import com.del.androidclasses.model.ClassesModel;
import java.util.List;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import com.del.androidclasses.R;
import android.widget.TextView;
import android.widget.ImageView;
import android.content.ClipboardManager;
import android.content.ClipData;
import java.util.ArrayList;
import android.widget.Toast;

public class ListviewSupportAdapter extends BaseAdapter implements Filterable {
	
	private Context context;
	private List<ClassesModel> items2;
	private LayoutInflater l_Inflater;
	private List<ClassesModel> itemsFilter2;
	private ValueFilter valueFilter2; 
	
	public ListviewSupportAdapter(Context context, List<ClassesModel> items2){
		this.context = context;
		this.items2 = items2;
		itemsFilter2 = items2;
		this.l_Inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		getFilter();
	}
	
	@Override
	public int getCount() {
		return items2.size();
	}

	@Override
	public Object getItem(int position) {
		return items2.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if(convertView == null ){
			convertView = LayoutInflater.from(context).inflate(R.layout.list_item2, parent, false);
			viewHolder = new ViewHolder(convertView);
			convertView.setTag(viewHolder);
		} else{
			viewHolder = (ViewHolder)convertView.getTag();
		}

		final ClassesModel cm = (ClassesModel) getItem(position);
		viewHolder.textView.setText(cm.getText());

		viewHolder.img.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View v) {
					ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE); 
					ClipData clip = ClipData.newPlainText("Copy", cm.getText());
					clipboard.setPrimaryClip(clip);
					Toast.makeText(context, "Succesfull : "+ cm.getText(), 2).show();
				}
			});

		return convertView;
	}

	public class ViewHolder{
		TextView textView;
		ImageView img;

		public ViewHolder(View itemView){
			textView = itemView.findViewById(R.id.title2);
			img = itemView.findViewById(R.id.img2);
		}
	}

	@Override
	public Filter getFilter() {
		if(valueFilter2 == null) {

			valueFilter2 = new ValueFilter();
		}

		return valueFilter2;
	}
	private class ValueFilter extends Filter {

		//Invoked in a worker thread to filter the data according to the constraint.
		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults results2 =new FilterResults();
			if(constraint!=null && constraint.length()>0){

				List<ClassesModel> filterList2 =new ArrayList<ClassesModel>();
				for(int i=0;i<itemsFilter2.size();i++){
					if((itemsFilter2.get(i).getText().toUpperCase())
					   .contains(constraint.toString().toUpperCase())) {
						ClassesModel classesModel2 = new ClassesModel();
						classesModel2.setText(itemsFilter2.get(i).getText());
						filterList2.add(classesModel2);
					}
				}
				results2.count=filterList2.size();
				results2.values=filterList2;
			}else{
				results2.count=itemsFilter2.size();
				results2.values=itemsFilter2;
			}
			return results2;
		}


		//Invoked in the UI thread to publish the filtering results in the user interface.
		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint,
									  FilterResults results2) {
			items2 =(List<ClassesModel>) results2.values;
			notifyDataSetChanged();
		}
	}
}
