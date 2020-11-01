# Codelab: Building Beautiful Transitions with Material Motion for Android

The Material motion system for Android is a set of transition patterns within
the [MDC-Android library](https://material.io/components/android) that can help
users understand and navigate an app, as described in the
[Material Design guidelines](https://material.io/design/motion/the-motion-system.html).
[Android motion document](https://material.io/develop/android/theming/motion)

This repo houses the source for the
[Material motion system codelab](https://codelabs.developers.google.com/codelabs/material-motion-android),
during which you will build Material transitions into an example email app
called Reply.

The starter code is available on the default `develop` branch, and the complete
code is available on the `complete` branch, which can you can checkout by
running `git checkout complete`.

<img src="screenshots/reply-transitions.gif" alt="Reply transitions"/>


### MaterialContainerTransform

#### Set transitionName into xml layout and transform find startView and endView
```
// fragment_email.xml - MaterialCardView
	...
	android:transitionName="@string/email_card_detail_transition_name"

// EmailFragment - onCreate
	sharedElementEnterTransition = MaterialContainerTransform().apply {
	    drawingViewId = R.id.nav_host_fragment
	    duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
	    scrimColor = Color.TRANSPARENT
	    setAllContainerColors(requireContext().themeColor(R.attr.colorSurface))
	}

// email_item_layout.xml - MaterialCardView
	android:transitionName="@{@string/email_card_transition_name(email.id)}"

// fragment_home.xml - RecyclerView
	android:transitionGroup="true"

// HomeFragment - onViewCreated
	postponeEnterTransition()
	view.doOnPreDraw { startPostponedEnterTransition() }

// HomeFragment - Before navigate to EmailFragment
	val emailCardDetailTransitionName = getString(R.string.email_card_detail_transition_name)
    val extras = FragmentNavigatorExtras(cardView to emailCardDetailTransitionName)

	exitTransition = MaterialElevationScale(false).apply {
	    duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
	}
	reenterTransition = MaterialElevationScale(true).apply {
	    duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
	}

	findNavController().navigate(directions, extras)
```

#### Set startView and endView manually
```
 enterTransition = MaterialContainerTransform().apply {
    startView = requireActivity().findViewById(R.id.fab)
    endView = emailCardView
    duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
    scrimColor = Color.TRANSPARENT
    containerColor = requireContext().themeColor(R.attr.colorSurface)
    startContainerColor = requireContext().themeColor(R.attr.colorSecondary)
    endContainerColor = requireContext().themeColor(R.attr.colorSurface)
}

```

### Slide
```
returnTransition = Slide().apply {
    duration = resources.getInteger(R.integer.reply_motion_duration_medium).toLong()
    addTarget(R.id.email_card_view)
}
```

### MaterialElevationScale
```
exitTransition = MaterialElevationScale(false).apply {
    duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
}
reenterTransition = MaterialElevationScale(true).apply {
    duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
}
```

### MaterialSharedAxis
```
exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
    duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
}
reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false).apply {
    duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
}
```

### MaterialFadeThrough
```
enterTransition = MaterialFadeThrough().apply {
    duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
}
```





