/*
The MIT License (MIT)

Copyright (c) 2020 Pierre Lindenbaum

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package com.github.lindenb.jvarkit.function;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * A  <code>Supplier&lt;T&gt;</code> caching the result 
 */
public class SupplierCached<T> implements Supplier<T> {
	private Supplier<T> delegate;//set to null when getSync is called
	private T cached = null;
	public SupplierCached(final Supplier<T> delegate) {
		this.delegate = Objects.requireNonNull(delegate);
	}
	
	private synchronized T getSync() {
		if(this.delegate == null) return this.cached;
		this.cached = this.delegate.get();
		this.delegate = null;// free delegate
		return this.cached;
	}
	
	@Override
	public T get() {
		return this.delegate == null ? this.cached: getSync();
	}
}
