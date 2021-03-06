package com.del.androidclasses.adapters;
import android.widget.BaseAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import java.util.List;
import com.del.androidclasses.model.ClassesModel;
import com.del.androidclasses.R;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Filterable;
import android.widget.Filter;
import java.util.ArrayList;
import android.content.ClipboardManager;
import android.content.ClipData;
import android.widget.Toast;
import com.google.android.material.snackbar.*;

public class ListviewAndroidxAdapter extends BaseAdapter implements Filterable {
	
	private Context context;
	private List<ClassesModel> items;
	private LayoutInflater l_Inflater;
	private List<ClassesModel> itemsFilter;
	private ValueFilter valueFilter;
	
	public ListviewAndroidxAdapter(Context context, List<ClassesModel> items){
		this.context = context;
		this.items = items;
		itemsFilter = items;
		this.l_Inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		getFilter();
	}
	
	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if(convertView == null ){
			convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
			viewHolder = new ViewHolder(convertView);
			convertView.setTag(viewHolder);
		} else{
			viewHolder = (ViewHolder)convertView.getTag();
		}
		
		final ClassesModel cm = (ClassesModel) getItem(position);
		viewHolder.textView.setText(cm.getText2());
		
		viewHolder.img.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View v) {
					ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE); 
					ClipData clip = ClipData.newPlainText("Copy", cm.getText2());
					clipboard.setPrimaryClip(clip);
					Snackbar.make(v, "Succesfully copy to clipboard  :  " + cm.getText2(), Snackbar.LENGTH_SHORT).show();
					
				}
			});
		
		return convertView;
	}
    
	public class ViewHolder{
		TextView textView;
		ImageView img;
		
		public ViewHolder(View itemView){
			textView = itemView.findViewById(R.id.title);
			img = itemView.findViewById(R.id.img);
		}
	}
	
	@Override
	public Filter getFilter() {
		if(valueFilter==null) {

			valueFilter=new ValueFilter();
		}

		return valueFilter;
	}
	private class ValueFilter extends Filter {

		//Invoked in a worker thread to filter the data according to the constraint.
		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults results=new FilterResults();
			if(constraint!=null && constraint.length()>0){
				
				List<ClassesModel> filterList=new ArrayList<ClassesModel>();
				for(int i=0;i<itemsFilter.size();i++){
					if((itemsFilter.get(i).getText2().toUpperCase())
					   .contains(constraint.toString().toUpperCase())) {
						ClassesModel classesModel = new ClassesModel();
						classesModel.setText2(itemsFilter.get(i).getText2());
						filterList.add(classesModel);
					}
				}
				results.count=filterList.size();
				results.values=filterList;
			}else{
				results.count=itemsFilter.size();
				results.values=itemsFilter;
			}
			return results;
		}


		//Invoked in the UI thread to publish the filtering results in the user interface.
		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint,
									  FilterResults results) {
			items =(List<ClassesModel>) results.values;
			notifyDataSetChanged();
		}
	}
}
