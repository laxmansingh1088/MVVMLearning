package dagger_room_contacts.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmlearning.R;
import com.example.mvvmlearning.databinding.ContactsListItemBinding;
import com.example.mvvmlearning.databinding.ContactsListItemDaggerBinding;

import java.util.List;

import dagger_room_contacts.models.ContactDaggerModel;
import room.ButtonEvents;
import room.interfaces.RecyclerViewOnClickListener;

public class ContactsDaggerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<ContactDaggerModel> contactModelList;
    private RecyclerViewOnClickListener recyclerViewOnClickListener;


    public class ContactsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
       /* private TextView contactName;
        private TextView contactEmail;
        private Button btnEdit;
        private Button btnDelete;*/
        ContactsListItemDaggerBinding contactsListItemBinding;

        public ContactsViewHolder(ContactsListItemDaggerBinding contactsListItemBinding) {
            super(contactsListItemBinding.getRoot());
            this.contactsListItemBinding = contactsListItemBinding;
         /*   contactName = v.findViewById(R.id.contact_name);
            contactEmail = v.findViewById(R.id.contact_email);
            btnEdit = v.findViewById(R.id.btn_edit);
            btnEdit.setOnClickListener(this);
            btnDelete = v.findViewById(R.id.btn_delete);
            btnDelete.setOnClickListener(this);*/

        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_edit:
                    recyclerViewOnClickListener.onRecycleViewClick(getAdapterPosition(), ButtonEvents.EDIT);
                    break;

                case R.id.btn_delete:
                    recyclerViewOnClickListener.onRecycleViewClick(getAdapterPosition(), ButtonEvents.DELETE);
                    break;
            }
        }
    }


    public ContactsDaggerAdapter(Context context, List<ContactDaggerModel> contactModelList, RecyclerViewOnClickListener recyclerViewOnClickListener) {
        this.context = context;
        this.contactModelList = contactModelList;
        this.recyclerViewOnClickListener = recyclerViewOnClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
     /*   View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contacts_list_item, parent, false);
        ContactsViewHolder vh = new ContactsViewHolder(v);
        return vh;*/

        ContactsListItemDaggerBinding contactsListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.contacts_list_item_dagger, parent, false);
        return new ContactsViewHolder(contactsListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ContactDaggerModel contactModel = contactModelList.get(position);
        if (holder instanceof ContactsViewHolder) {

            ((ContactsViewHolder) holder).contactsListItemBinding.setContactDaggerModel(contactModel);
            /*((ContactsViewHolder) holder).contactName.setText(contactModel.getContactName());
            ((ContactsViewHolder) holder).contactEmail.setText(contactModel.getContactEmail());*/
        }
    }

    @Override
    public int getItemCount() {
        return contactModelList == null ? 0 : contactModelList.size();
    }


}
