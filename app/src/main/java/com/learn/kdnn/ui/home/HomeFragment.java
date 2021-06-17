package com.learn.kdnn.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.learn.kdnn.AppCacheManage;
import com.learn.kdnn.MainActivity;
import com.learn.kdnn.MainViewModel;
import com.learn.kdnn.R;
import com.learn.kdnn.databinding.FragmentHomeBinding;
import com.learn.kdnn.model.CartItem;
import com.learn.kdnn.model.Product;
import com.learn.kdnn.ui.product.ProductDetailsFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HomeFragment extends Fragment {

    private MainViewModel mainViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        List<Product> products = getProducts();


        RecyclerView productViews = binding.products;
        productViews.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        HomeItemViewAdapter homeItemViewAdapter = new HomeItemViewAdapter(products, getContext());
        productViews.setAdapter(homeItemViewAdapter);
        homeItemViewAdapter.setOnItemClick((position, item, index) -> {
            Bundle bundle = new Bundle();
            bundle.putInt(ProductDetailsFragment.PRODUCT_INDEX, index);
            NavController navController = Navigation.findNavController((MainActivity) getContext(), R.id.nav_host_fragment_content_main);
            navController.navigate(R.id.action_nav_home_to_nav_product_details, bundle);
        });

        binding.gridView.setOnClickListener(v -> {
            Boolean isUsingGridView = mainViewModel.getIsUsingGridView().getValue();
            if (isUsingGridView) {
                //using list view
                homeItemViewAdapter.setUsingListView(true);
                productViews.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                binding.gridView.setImageResource(R.drawable.ic_baseline_view_module_24);
            } else {
                //change to grid view
                productViews.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
                binding.gridView.setImageResource(R.drawable.ic_baseline_view_list_24);
                homeItemViewAdapter.setUsingListView(false);

            }
            homeItemViewAdapter.notifyDataSetChanged();
            mainViewModel.getIsUsingGridView().setValue(!isUsingGridView);
        });

        binding.btnFiilter.setOnClickListener(v -> showFilterOptionsFragment());

        Spinner spinner = binding.spinner;
        List<String> categories = new ArrayList<>();
        categories.add("Fast food");
        categories.add("Drink");
        categories.add("Cake");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, categories);
        spinner.setAdapter(adapter);
        return root;
    }

    @NotNull
    private List<Product> getProducts() {
        final String uri = "https://images.pexels.com/photos/842571/pexels-photo-842571.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940";
        Product product = new Product(1, "Hambugur", 12.00, 5.0, "Fast Food", "https://images.pexels.com/photos/1516415/pexels-photo-1516415.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500", null);
        Product product1 = new Product(2, "Hambugur", 1.25, 12.0, "Fast Food", uri, null);
        String primaryImgurl = "https://images.pexels.com/photos/1370939/pexels-photo-1370939.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500";
        Product product2 = new Product(3, "Hambugur", 3.5, 10.0, "Fast Food", "https://images.pexels.com/photos/1289247/pexels-photo-1289247.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500", null);
        Product product3 = new Product(4, "Hambugur", 2.2, 0.0, "Drink", "https://images.pexels.com/photos/338713/pexels-photo-338713.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500", null);
        Product product4 = new Product(5, "Hambugur", 1.26, 0.0, "Fast Food", "https://images.pexels.com/photos/704569/pexels-photo-704569.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500", null);
        Product product5 = new Product(6, "Hambugur", 3.25, 0.0, "Fast Food", primaryImgurl, null);
        Product product6 = new Product(7, "Capuchino", 5.00, 5.0, "Fast Food", "https://images.pexels.com/photos/1437629/pexels-photo-1437629.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500", null);
        Product product7 = new Product(8, "Capuchino", 4.00, 5.0, "Fast Food", "https://images.pexels.com/photos/6858636/pexels-photo-6858636.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500", null);
        Product product8 = new Product(9, "Capuchino", 1.35, 5.0, "Fast Food", "https://images.pexels.com/photos/339696/pexels-photo-339696.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500", null);
        Product product9 = new Product(10, "Capuchino", 1.30, 5.0, "Fast Food", "https://images.pexels.com/photos/7678003/pexels-photo-7678003.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500", null);
        Product product10 = new Product(32, "Capuchino", 6.00, 5.0, "Fast Food", "https://images.pexels.com/photos/7678003/pexels-photo-7678003.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500", null);
        Product product11 = new Product(53, "Capuchino", 7.50, 5.0, "Fast Food", "https://images.pexels.com/photos/7678003/pexels-photo-7678003.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500", null);
        Product product12 = new Product(212, "Capuchino", 3.250, 5.0, "Fast Food", "https://images.pexels.com/photos/7678003/pexels-photo-7678003.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500", null);
        Product product13 = new Product(45, "Capuchino", 1.25, 5.0, "Fast Food", "https://images.pexels.com/photos/7678003/pexels-photo-7678003.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500", null);
        List<Product> products = Arrays.asList(product, product1, product2, product3, product4, product5, product6, product7, product8, product9, product10, product11, product12, product13);
        AppCacheManage.products = products;

        addProductToBag(mainViewModel);
        return products;
    }


    private HashMap<Integer, Object> addProductToBag(MainViewModel mainViewModel) {
        HashMap<Integer, Object> bag = mainViewModel.getBag().getValue();
        if (bag == null) {
            bag = new HashMap<>();
            mainViewModel.getBag().setValue(bag);
        }

        Product p = AppCacheManage.products.get(0);
        Product p2 = AppCacheManage.products.get(1);
        bag.put(p.getId(), new CartItem(p, 5));
        bag.put(p2.getId(), new CartItem(p2, 3));


        return bag;
    }

    private void showFilterOptionsFragment() {
        FragmentManager manager = ((MainActivity) getContext()).getSupportFragmentManager();
        new FilterOptionsFragment().show(manager, "filter options fragment");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}