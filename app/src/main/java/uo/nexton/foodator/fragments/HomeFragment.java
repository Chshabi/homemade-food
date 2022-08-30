package uo.nexton.foodator.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import uo.nexton.foodator.R;
import uo.nexton.foodator.adapters.SliderAdapterExample;
import uo.nexton.foodator.adapters.ProductsAdapter;
import uo.nexton.foodator.models.Products;
import uo.nexton.foodator.models.SliderItem;

public class HomeFragment extends Fragment {

    View root;
    private SliderAdapterExample adapter;
    RecyclerView Products_rcv;
    ProductsAdapter productsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);

        SliderView sliderView = root.findViewById(R.id.homeScreenSlider);
        adapter = new SliderAdapterExample(getContext().getApplicationContext());

        SliderItem sliderItem1 = new SliderItem();
        sliderItem1.setImageUrl("https://img.freepik.com/free-psd/web-banner-template-restaurant-memphis-style_23-2148168805.jpg?w=826&t=st=1660599330~exp=1660599930~hmac=8ddd21ff6b564b2720e754ca6494d586f44e2e200af0a1140a26d06ae0a4fd9e");
        adapter.addItem(sliderItem1);

        SliderItem sliderItem2 = new SliderItem();
        sliderItem2.setImageUrl("https://img.freepik.com/free-psd/web-banner-template-restaurant-memphis-style_23-2148168804.jpg?w=826&t=st=1660599392~exp=1660599992~hmac=20e5d41c57f724d6abfba71dc830d8853546ba06fa88651f54f4105106f0c6f8");
        adapter.addItem(sliderItem2);

        SliderItem sliderItem3 = new SliderItem();
        sliderItem3.setImageUrl("https://img.freepik.com/free-psd/web-banner-template-restaurant-memphis-style_23-2148168821.jpg?w=826&t=st=1660599425~exp=1660600025~hmac=2e841c199de2884d7ed555e8dfd4fa7688e123b47d234da9018ecb7f74e67776");
        adapter.addItem(sliderItem3);

        SliderItem sliderItem4 = new SliderItem();
        sliderItem4.setImageUrl("https://img.freepik.com/free-psd/web-banner-template-restaurant-memphis-style_23-2148168802.jpg?w=826&t=st=1660599492~exp=1660600092~hmac=4a3defd8079e92e59922cdd505bcbe9a2970c9f2fc77bf75717c92bb0506f566");
        adapter.addItem(sliderItem4);

        SliderItem sliderItem5 = new SliderItem();
        sliderItem5.setImageUrl("https://img.freepik.com/free-psd/web-banner-template-restaurant-memphis-style_23-2148168822.jpg?w=826&t=st=1660599577~exp=1660600177~hmac=37065c9cec7e6a7f30a31d744d745a04a69ef9266e2df69090cd5b4ab6d0842a");
        adapter.addItem(sliderItem5);

        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.SWAP);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setScrollTimeInSec(2);
        sliderView.startAutoCycle();

        Products_rcv = root.findViewById(R.id.topProducts_rcv);
        Products_rcv.setLayoutManager(new GridLayoutManager(getContext().getApplicationContext(), 2));

        FirebaseRecyclerOptions<Products> options =
                new FirebaseRecyclerOptions.Builder<Products>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("products"), Products.class)
                        .build();

        productsAdapter = new ProductsAdapter(options);
        Products_rcv.setAdapter(productsAdapter);

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        Products_rcv.getRecycledViewPool().clear();
        productsAdapter.notifyDataSetChanged();
        productsAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        productsAdapter.stopListening();
    }
}