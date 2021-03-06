package com.learn.kdnn;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.learn.kdnn.model.Product;
import com.learn.kdnn.model.ShippingAddress;
import com.learn.kdnn.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
public class MainViewModel extends ViewModel {

    @Setter
    private List<Product> products;

    private MutableLiveData<HashMap<Long, Object>> bag;

    private MutableLiveData<Boolean> isUsingGridView;

    private MutableLiveData<User> user;

    private MutableLiveData<Integer> shippingMethod;

    private MutableLiveData<ShippingAddress> shippingAdress;

    public MainViewModel() {
        bag = new MutableLiveData<>();
        bag.setValue(new HashMap<>());
        user = new MutableLiveData<>();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            FirebaseFirestore.getInstance()
                    .collection("users")
                    .document(FirebaseAuth.getInstance().getUid())
                    .addSnapshotListener((value, error) -> {
                        User u;
                        if ( value !=null &&value.exists() && value.getData() != null) {
                            Map<String, Object> data = value.getData();
                            u = new User();
                            u.setAddress((String) data.get("address"));
                            u.setPhoneNumber((String) data.get("phone"));
                            u.setRole((String)data.get("role"));
                            user.setValue(u);
                        } else {
                            user.setValue(new User());
                        }

                    });
        }
        shippingMethod = new MutableLiveData<>();
        shippingMethod.setValue(-1);
        shippingAdress = new MutableLiveData<>();
        isUsingGridView = new MutableLiveData<>();
        isUsingGridView.setValue(true);


    }
}
